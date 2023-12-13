package ma.aftas.aflasclubapi.web.service.impl;

import jakarta.transaction.Transactional;

import lombok.extern.log4j.Log4j2;
import ma.aftas.aflasclubapi.dto.HuntingRequestDto;
import ma.aftas.aflasclubapi.dto.HuntingResponseDto;
import ma.aftas.aflasclubapi.entity.*;
import ma.aftas.aflasclubapi.exception.business.BadRequestException;
import ma.aftas.aflasclubapi.exception.business.NotFoundException;
import ma.aftas.aflasclubapi.exception.business.UserNotFoundException;
import ma.aftas.aflasclubapi.mappers.PodiumMapper;
import ma.aftas.aflasclubapi.web.repository.*;
import ma.aftas.aflasclubapi.web.service.HuntingService;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


@Service
@Transactional
public class HuntingServiceImpl implements HuntingService {
    private final CompetitionRepository competitionRepository;
    private final FishRepository fishRepository ;
    private final MemberRepository memberRepository ;
    private final RankingRepository rankingRepository;
    private final HuntingRepository huntingRepository;
    private Logger log = Logger.getLogger(HuntingServiceImpl.class.getName());
    public HuntingServiceImpl(CompetitionRepository competitionRepository, FishRepository fishRepository, MemberRepository memberRepository, RankingRepository rankingRepository, HuntingRepository huntingRepository) {
        this.competitionRepository = competitionRepository;
        this.fishRepository = fishRepository;
        this.memberRepository = memberRepository;
        this.rankingRepository= rankingRepository;
        this.huntingRepository = huntingRepository;
    }

    @Override
    public HuntingResponseDto addNewHuntingToMember(
            String codeCompetition, HuntingRequestDto huntingRequestDto) {
        //: FIRST I NEED TO VALIDATE  CODE COMPETITION
        if (codeCompetition.isBlank() || codeCompetition.isEmpty()){
            throw  new BadRequestException("Please Provide Code competition ");
        }


        // : IF THE FISH ALREADY EXISTS  CHECK THE MEMBER ALSO OR THROW EXCEPTION
        Member member = this.memberRepository.findByIdentityNumber(
                huntingRequestDto.getIdentityNumber()).orElseThrow(
                        ()-> new UserNotFoundException(
                                "Member with Identity number :"+huntingRequestDto.getIdentityNumber()+" does not exists ")
        );
        // : NEXT CHECK THE IF THE COMPETITION ALREADY EXISTS ELSE THROW EXCEPTION

        Competition competition = this.competitionRepository.listerLesCompetitionParCode(codeCompetition).orElseThrow(
                ()-> new NotFoundException("this competition doesn't exit's ")
        );


        // :FIND RANKING BY COMPETITION AND MEMBER
        Ranking ranking= this.rankingRepository.findRankingsByCompetitionIdAndMemberId(competition.getCode(), member.getNum()).orElseThrow(()->
            new NotFoundException("The Member with identityNumber "+huntingRequestDto.getIdentityNumber() +" and competition "+codeCompetition +" they didn't exists ")
        );

        // : IF THE COMPETITION ALREADY EXISTS  CHECK THE FISH ALSO OR THROW EXCEPTION
        Fish fish = this.fishRepository.findById(huntingRequestDto.getFishId()).orElseThrow(
                ()-> new NotFoundException("this Fish with id "+huntingRequestDto.getFishId()+"doesn't exists ")
        );


        AtomicBoolean exists = new AtomicBoolean(false);
        AtomicInteger score = new AtomicInteger(0);

        ranking.getMember().getHuntings().forEach(

                 (hunting)->{
                     if (hunting.getFish().getId().equals(huntingRequestDto.getFishId())){
                         hunting.setNumberOfFish(hunting.getNumberOfFish()+1);
                         score.set(score.get()+hunting.getNumberOfFish()*hunting.getFish().getLevel().getPoints());
                         exists.set(true);
                     }
                 });

        if (!exists.get()){
            Hunting newHunting = new Hunting();
            newHunting.setNumberOfFish(1);
            newHunting.setFish(fish);
            newHunting.setMember(member);
            newHunting.setCompetition(competition);
            this.huntingRepository.save(newHunting);

        }

        //: LAST COLLECT OF COUNT THE SCORE BASED ON HUNT.NUMBER * FISH.LEVEL + *** = SCORE


        ranking.setScore(score.get());
        log.info("SCORE : "+score.get());
        this.rankingRepository.findRankingsByCompetitionId(codeCompetition,null).forEach((rank -> {
            //TODO : RANKING
        }));


        HuntingResponseDto huntingResponseDto = new HuntingResponseDto();


        huntingResponseDto.setMessage("your hunt is done  Successfully Bravo !");
        huntingResponseDto.setPodiumDto(PodiumMapper.INSTANCE.toDto(ranking));

        this.rankingRepository.save(ranking);
        log.info("HuntingResponseDto : "+huntingResponseDto.toString());




        return huntingResponseDto;
    }
}
