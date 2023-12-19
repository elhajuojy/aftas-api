package ma.aftas.aflasclubapi.web.service.impl;

import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import ma.aftas.aflasclubapi.dto.MemberDto;
import ma.aftas.aflasclubapi.dto.MemberResponseDto;
import ma.aftas.aflasclubapi.entity.Member;
import ma.aftas.aflasclubapi.exception.business.AlreadyExistsException;
import ma.aftas.aflasclubapi.exception.business.BadRequestException;
import ma.aftas.aflasclubapi.exception.business.UserNotFoundException;
import ma.aftas.aflasclubapi.mappers.MemberMapper;
import ma.aftas.aflasclubapi.util.AftasUtil;
import ma.aftas.aflasclubapi.web.repository.MemberRepository;
import ma.aftas.aflasclubapi.web.service.MemberService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ma.aftas.aflasclubapi.util.AftasUtil.getPagePathQueryChecker;

@Service
@Transactional
@Log4j2
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository ;
    private MemberMapper memberMapper;
    Logger logger = org.slf4j.LoggerFactory.getLogger(MemberServiceImpl.class);

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = MemberMapper.INSTANCE;
    }

    @Override
    public MemberResponseDto addMember(MemberDto memberDto) {

        if (memberDto!=null){
            memberDtoValidation(memberDto);
            // : SAVE MEMBER
            // : CHECK IF THE MEMBER ALREADY EXISTS

            Optional<Member> checkMember = this.memberRepository.findByIdentityNumber(memberDto.getIdentityNumber().trim());

            if (checkMember.isPresent()){
                throw  new AlreadyExistsException("The member with IdentityNumber :  "+memberDto.getIdentityNumber()+" already Exists ");
            }

            Member member = this.memberMapper.toEntity(memberDto);
            member.setAccessionDate(LocalDateTime.now());
            //TODO:GENERATE NUM FOR MEMBER SO HE CAN USED IT
            Integer code = 0;
            member = this.memberRepository.save(member);
            return this.memberMapper.toDtoResponse(member);
        }else {
            throw new NullPointerException("member object is null make sure to make instance of member ");
        }

    }

    private void memberDtoValidation(MemberDto memberDto) {
        Map<String,String> errors = new HashMap<>();

        if (memberDto.getName().isBlank()){
            errors.put("name","Please provide your name");
        }
        if (memberDto.getFamilyName().isBlank()){
            errors.put("name","Please provide your familyName");
        }
        if (memberDto.getNationality().isBlank()){
            errors.put("name","Please provide your Nationality ");
        }
        if (memberDto.getIdentityDocumentType()==null){
            errors.put("IdentityDocumentType","Please provide your IdentityDocumentType");
        }

        if (!errors.isEmpty()){
            throw new BadRequestException("add Member validation exception's ",errors);
        }
    }




    @Override
    public Optional<MemberDto> findMemberByNum(Integer num) {
        if (num < 1){
            throw  new IllegalArgumentException("Please provide your' num the num should be positive  number ");
        }
        this.memberRepository.findAllByNum(num).orElseThrow(
                ()->new UserNotFoundException("The member with num "+num+" was not found")
        );
        return null;
    }

    //:  recherche d’un adhérent par numéro, nom, ou prénom .
    @Override
    public MemberDto findMemberByMoreThanParam(Map<String, String> queryParams) {
        Member member  = new Member();
        //: CHECK IF THE QUERY PARAMS IS EMPTY OR NOT HAVING THE REQUIRED PARAMS SUCH AS NAME , FAMILYNAME , NUM
        if (queryParams.isEmpty() && !queryParams.containsKey("name") && !queryParams.containsKey("familyName") && !queryParams.containsKey("num")){
            throw  new IllegalArgumentException("Please provide your' param such as name , familyName , num ");
        }
        //:page and size



        if (queryParams.containsKey("name")){

          member =this.memberRepository.findByName(queryParams.get("name")).orElseThrow(
                 ()->new UserNotFoundException("The member with name "+queryParams.get("name")+" was not found")
          );

        }
        if (queryParams.containsKey("familyName")){
             member = this.memberRepository.findByFamilyName(queryParams.get("familyName")).orElseThrow(
                    ()->new UserNotFoundException("The member with familyName "+queryParams.get("familyName")+" was not found")
            );
        }
        if (queryParams.containsKey("num")){
             member = this.memberRepository.findByIdentityNumber(queryParams.get("num")).orElseThrow(
                    ()->new UserNotFoundException("The member with num "+queryParams.get("num")+" was not found")
            );
        }


        return MemberMapper.INSTANCE.toDto(member);
    }

    @Override
    public Page<MemberDto> listerLesMembres(Map<String, String> queryParams) {
        AftasUtil.PagePathQueryChecker pagePathQueryChecker = getPagePathQueryChecker(queryParams);
        PageRequest pageRequest ;
        pageRequest = PageRequest.of(pagePathQueryChecker.page(), pagePathQueryChecker.size());
        return this.memberRepository.findAll(pageRequest).map(MemberMapper.INSTANCE::toDto);
    }
}
