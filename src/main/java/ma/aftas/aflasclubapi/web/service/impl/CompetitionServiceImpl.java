package ma.aftas.aflasclubapi.web.service.impl;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import ma.aftas.aflasclubapi.dto.*;
import ma.aftas.aflasclubapi.entity.Competition;
import ma.aftas.aflasclubapi.entity.Member;
import ma.aftas.aflasclubapi.enums.StatusCompeition;
import ma.aftas.aflasclubapi.mappers.CompetitionMapper;
import ma.aftas.aflasclubapi.mappers.MemberMapper;
import ma.aftas.aflasclubapi.util.AftasUtil;
import ma.aftas.aflasclubapi.web.repository.CompetitionRepository;
import ma.aftas.aflasclubapi.web.repository.MemberRepository;
import ma.aftas.aflasclubapi.web.service.CompetitionService;
import ma.yc.api.common.exception.business.*;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static ma.aftas.aflasclubapi.util.AftasUtil.getPagePathQueryChecker;

@Service
@Transactional
@Log4j2
public class CompetitionServiceImpl implements CompetitionService  {
    private final CompetitionRepository competitionRepository;

    private final MemberRepository memberRepository;

    private final CompetitionMapper competitionMapper;
    private Faker faker;

    Logger logger = org.slf4j.LoggerFactory.getLogger(CompetitionServiceImpl.class);

    public CompetitionServiceImpl(CompetitionRepository competitionRepository,MemberRepository memberRepository) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = CompetitionMapper.INSTANCE;
        this.memberRepository = memberRepository;
        this.faker = new Faker();

    }

    @Override
    public CompetitionDto ajouterCompetition(CompetitionRequestDto competitionRequestDto) {
        ajouterCompetitionValidationDto(competitionRequestDto);

        // : CHECK IF THERE'S ALREADY EVENT IN THE SAME DATE IF EXIT'S THROW ALREADY RESERVED EXCEPTION
        if(this.competitionRepository.listerLesCompetitionParDate(competitionRequestDto.getDate()).isPresent()){
            throw  new AlreadyExistsException("THERE'S ALREADY COMPETITION PLANNED IN THIS DATE THAT YOU HAVE PROVIDED ");
        }

        Competition competition  = this.competitionMapper.toEntity(competitionRequestDto);
        //:pattern : cityCode-day-month-year example : CAS-12-12-20 remove the first 2 digits of the year
        String yearPattern = competition.getDate().getYear()+"";
        String year = yearPattern.substring(2,yearPattern.length());
        String code = competition.getLocation().substring(0,3);
        competition.setCode(code+"-"+competition.getDate().getDayOfMonth()+"-"+competition.getDate().getMonthValue()+"-"+year);
        competition.setNumberOfParticipants(0);
        logger.info("ajouter competition  code "+competition.getCode());



        return this.competitionMapper.toDto(this.competitionRepository.save(competition));


    }

    private void ajouterCompetitionValidationDto(CompetitionRequestDto competitionRequestDto) {
        Map<String,String> errors = new HashMap<>();
        if (competitionRequestDto != null){
            if (competitionRequestDto.getDate() == null){
                errors.put("date","Date de competition est obligatoire");
            }
            if (competitionRequestDto.getStartTime() == null){
                errors.put("startTime","Heure de debut de competition est obligatoire");
            }
            if (competitionRequestDto.getEndTime() == null){
                errors.put("endTime","Heure de fin de competition est obligatoire");
            }
            if (competitionRequestDto.getLocation() == null || competitionRequestDto.getLocation().isEmpty()){
                errors.put("location","Location de competition est obligatoire");
            }
            if (competitionRequestDto.getAmount() == null){
                errors.put("amount","Montant de competition est obligatoire");
            }
            if (!errors.isEmpty()){
                throw new BadRequestException("Input validation exception's ",errors);
            }
        }
    }

    // : inscription Member Dans une Competition (check si membre exists ou non  )
    @Override
    public MemberCompetitionResponse inscriptionMembreDansCompetition(MemberCompetitionRequest memberCompetitionRequest) {
        inscriptionMembreDansCompetitionValidation(memberCompetitionRequest);
        Optional<Member> member =  this.memberRepository.findByIdentityNumber(memberCompetitionRequest.getIdentityNumber());
        if(member.isEmpty()){
            throw  new UserNotFoundException("Member not found with this identity number");
        }

        //:: GET COMPETITION
        Optional<Competition> competition = this.competitionRepository.listerLesCompetitionParCode(memberCompetitionRequest.getCodeCompetition());
        if(competition.isEmpty()){
            throw  new NotFoundException("Competition not found with this code");
        }
        // : the member and the competition are both exists now let's do our business logic
        // : check if the member is already registered in this competition or not
        if (competition.get().getMembers().contains(member.get())){
            log.info("member is already registered in this competition");
            throw  new AlreadyExistsException("You are already registered in this competition");
        }


        // : check if the competition start date is before today's date and 24h before the competition
        // start date so the member can register to the platform
        LocalDate competitionDate = competition.get().getDate();
        LocalDate legalDateToRegisterInCompetition = LocalDate.now().minusDays(1);
        log.info("competition date "+competitionDate +" legal date to register "+legalDateToRegisterInCompetition) ;
        if (competitionDate.isBefore(legalDateToRegisterInCompetition)){
            throw  new OutOfTimeExpection("You can't register to this competition because it's already started or it's less than 24h before the competition");
        }

        //: NOW YOU CAN REGISTER MEMBER TO THE COMPETITION WITH SUCCESS
        competition.get().getMembers().add(member.get());

        MemberCompetitionResponse memberCompetitionResponse = new MemberCompetitionResponse();
        memberCompetitionResponse.setMemberDto(MemberMapper.INSTANCE.toDto(member.get()));
        memberCompetitionResponse.setCompetitionDto(CompetitionMapper.INSTANCE.toDto(competition.get()));


        return memberCompetitionResponse;
    }

    private void inscriptionMembreDansCompetitionValidation(MemberCompetitionRequest memberCompetitionRequest) {
        Map<String,String> errors = new HashMap<>();
        if (memberCompetitionRequest.getCodeCompetition().isBlank()|| memberCompetitionRequest.getCodeCompetition().isEmpty())
        {
            errors.put("codeCompetition","Please Provide code competition");

        }
        if (memberCompetitionRequest.getIdentityNumber().isEmpty()|| memberCompetitionRequest.getIdentityNumber().isBlank()){
            errors.put("identityNumber","Please Provide your ");
        }
        if (!errors.isEmpty()){
            throw new BadRequestException("Input validation exception's ",errors);
        }
    }

    @Override
    public Page<CompetitionDto> ListerLesCompetition(Map<String, String> queryParams) {
        AftasUtil.PagePathQueryChecker pagePathQueryChecker = getPagePathQueryChecker(queryParams);
        PageRequest pageRequest ;

        pageRequest = PageRequest.of(pagePathQueryChecker.page(), pagePathQueryChecker.size());

        if (queryParams.containsKey("status")) {
            return getLesCompetitionParStatus(queryParams.get("status"), pageRequest);
        }
        Page<Competition> competitions = this.competitionRepository.findAll(pageRequest);
        return competitions.map(CompetitionMapper.INSTANCE::toDto);
    }

    @Override
    public CompetitionDto getCompetitionByCode(String code) {
        return this.competitionRepository.listerLesCompetitionParCode(code).map(competition -> {
            CompetitionDto competitionDto = CompetitionMapper.INSTANCE.toDto(competition);
            competitionDto.setStatus(StatusCompeition.ENCOURS);
            return competitionDto;
        }).orElseThrow(()->new NotFoundException("Competition not found with this code"));
    }

    private Page<CompetitionDto> getLesCompetitionParStatus(String status, PageRequest pageRequest) {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());

            switch (status){
                case "encour" -> {
                    return this.competitionRepository.listerLesCompetitionEncour(today, pageRequest).map(competition ->{
                       CompetitionDto competitionDto =  CompetitionMapper.INSTANCE.toDto(competition);
                       competitionDto.setStatus(StatusCompeition.ENCOURS);
                       return competitionDto;
                    });
                }
                case "avenir"->{
                    return this.competitionRepository.listerLesCompetitionAvenir(today,pageRequest).map(
                            competition -> {
                                CompetitionDto competitionDto = CompetitionMapper.INSTANCE.toDto(competition);
                                competitionDto.setStatus(StatusCompeition.AVENIR);
                                return competitionDto;
                            });
                }
                case "ferme"->{
                   return this.competitionRepository.listerLesCompetitionFerme(today, pageRequest).map(
                           competition -> {
                               CompetitionDto competitionDto = CompetitionMapper.INSTANCE.toDto(competition);
                               competitionDto.setStatus(StatusCompeition.FERME);
                               return competitionDto;

                           });
                }
                default -> throw new  IllegalArgumentException(status);

            }

    }



}
