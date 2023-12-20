package ma.aftas.aflasclubapi.web.service.impl;


import jakarta.transaction.Transactional;

import ma.aftas.aflasclubapi.dto.HuntingRequestDto;
import ma.aftas.aflasclubapi.dto.HuntingResponseDto;
import ma.aftas.aflasclubapi.entity.*;

import ma.aftas.aflasclubapi.web.repository.*;
import ma.aftas.aflasclubapi.web.service.HuntingService;
import ma.yc.api.common.exception.business.BadRequestException;
import ma.yc.api.common.exception.business.NotFoundException;
import ma.yc.api.common.exception.business.UserNotFoundException;
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
    private final Logger log = Logger.getLogger(HuntingServiceImpl.class.getName());
    public HuntingServiceImpl(CompetitionRepository competitionRepository, FishRepository fishRepository,
                              MemberRepository memberRepository,
                              RankingRepository rankingRepository, HuntingRepository huntingRepository
                              ) {
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
        Member member = this.memberRepository.findAllByNum(
                huntingRequestDto.getNum()).orElseThrow(
                        ()-> new UserNotFoundException(
                                "Member with Identity number :"+huntingRequestDto.getNum()+" does not exists ")
        );
        // : NEXT CHECK THE IF THE COMPETITION ALREADY EXISTS ELSE THROW EXCEPTION

        Competition competition = this.competitionRepository.listerLesCompetitionParCode(codeCompetition).orElseThrow(
                ()-> new NotFoundException("this competition doesn't exit's ")
        );


        // :FIND RANKING BY COMPETITION AND MEMBER

        Ranking ranking= findRankingByMemberAndCompetition( competition , member) ;

        // : IF THE COMPETITION ALREADY EXISTS  CHECK THE FISH ALSO OR THROW EXCEPTION
        Fish fish = this.fishRepository.findById(huntingRequestDto.getFishId()).orElseThrow(
                ()-> new NotFoundException("this Fish with id "+huntingRequestDto.getFishId()+" doesn't exists ")
        );

        //: CHECK THE WEIGHT OF THE FISH IF IT'S GREATER THAN THE MAX WEIGHT OF THE FISH OR LESS THAN THE MIN WEIGHT OF THE FISH
        Double maxWeight = 100.0;
        Double minWeight = 10.0;
        if (huntingRequestDto.getWeight() > maxWeight || huntingRequestDto.getWeight() < minWeight){
            throw new BadRequestException("The weight of the fish must be between "+minWeight+" and "+maxWeight);
        }

        AtomicBoolean exists = new AtomicBoolean(false);
        log.info("GET HUNTING MEMBER OF FISH : "+huntingRequestDto.getNumber_of_fish());
        ranking.getMember().getHuntings().forEach((hunting)->{
                     if (hunting.getFish().getId().equals(huntingRequestDto.getFishId())){
                         hunting.setNumberOfFish(hunting.getNumberOfFish()+ huntingRequestDto.getNumber_of_fish());
                         log.info("hunting : "+hunting.getFish().getName()+" "+hunting.getNumberOfFish()+" "+hunting.getMember().getName()+" "+hunting.getCompetition().getCode());
                         exists.set(true);
                         this.huntingRepository.save(hunting);
                     }
        });

        saveHuntingIfNotExists(huntingRequestDto, exists, fish, member, competition);

//        Ranking ranking = findRankingByMemberAndCompetition(competition , member);
        //TODO :BUG FIX:  RANKING RETURN OLD VALUE SCORE - 1 NEW HUNTING SCORES
        log.info("GET RANKING SCORE : "+ranking.getScore());

        boolean isUpdated =  updateRankingScore(codeCompetition);
        log.info("IS UPDATED : "+isUpdated);
        if (!isUpdated){
            throw new RuntimeException("Error while updating ranking score ");
        }
        HuntingResponseDto huntingResponseDto = new HuntingResponseDto();
        huntingResponseDto.setMessage("your hunt is done  Successfully Bravo !");
        return huntingResponseDto;
    }

    private void saveHuntingIfNotExists(HuntingRequestDto huntingRequestDto, AtomicBoolean exists, Fish fish, Member member, Competition competition) {
        if (!exists.get()){
            Hunting newHunting = new Hunting();
            newHunting.setNumberOfFish(huntingRequestDto.getNumber_of_fish());
            newHunting.setFish(fish);
            newHunting.setMember(member);
            newHunting.setCompetition(competition);
            log.info("newHunting : "+newHunting.getFish().getName()+" "+newHunting.getNumberOfFish()+" "+newHunting.getMember().getName()+" "+newHunting.getCompetition().getCode());
            this.huntingRepository.save(newHunting);
        }
    }

    @Override
    public HuntingResponseDto getHuntingByCompetitionId(String code) {
        return null;
    }


    private Ranking findRankingByMemberAndCompetition(Competition competition ,Member member) {
        return this.rankingRepository.findRankingsByCompetitionIdAndMemberId(competition.getCode(), member.getNum()).orElseThrow(()->
                new NotFoundException("The Member with identityNumber "+member.getIdentityNumber() +" and competition "+competition.getCode() +" they didn't exists ")
        );
    }

    // : sum ranking based on the competition and
    public boolean updateRankingScore(String codeCompetition ){
        // : FIND RANKING BY COMPETITION AND MEMBER THAN LOOP INTO ALL MEMBER'S HUNTING AND SUM THE SCORE AND UPDATE THE RANKING
        Competition  competition= this.competitionRepository.listerLesCompetitionParCode(codeCompetition).orElseThrow(()-> new NotFoundException("this competition doesn't exit's "));
        competition.getMembers().forEach((member)->{
            AtomicInteger score = new AtomicInteger(0);
            for (Hunting hunting : member.getHuntings()) {
                score.set(score.get() + hunting.getNumberOfFish() * hunting.getFish().getLevel().getPoints());
            }
            Ranking ranking = this.rankingRepository.findRankingsByCompetitionIdAndMemberId(competition.getCode(), member.getNum()).orElseThrow(()->
                    new NotFoundException("The Member with identityNumber "+member.getIdentityNumber() +" and competition "+codeCompetition +" they didn't exists ")
            );
            ranking.setScore(score.get());
            this.rankingRepository.save(ranking);
        });
        return true;
    }

}
