package ma.aftas.aflasclubapi.web.service.impl;

import jakarta.transaction.Transactional;
import ma.aftas.aflasclubapi.dto.PodiumCompetitionDto;
import ma.aftas.aflasclubapi.dto.PodiumDto;
import ma.aftas.aflasclubapi.entity.Competition;
import ma.aftas.aflasclubapi.entity.Ranking;
import ma.aftas.aflasclubapi.mappers.PodiumMapper;
import ma.aftas.aflasclubapi.util.AftasUtil;
import ma.aftas.aflasclubapi.web.repository.CompetitionRepository;
import ma.aftas.aflasclubapi.web.repository.RankingRepository;
import ma.aftas.aflasclubapi.web.service.PodiumService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static ma.aftas.aflasclubapi.util.AftasUtil.getPagePathQueryChecker;

@Service
@Transactional
public class PodiumServiceImpl implements PodiumService {
    private RankingRepository rankingRepository ;
    private CompetitionRepository competitionRepository;

    public PodiumServiceImpl(RankingRepository rankingRepository, CompetitionRepository competitionRepository) {
        this.rankingRepository = rankingRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Page<PodiumDto> affichePodium(Map<String, String> queryParams) {
        AftasUtil.PagePathQueryChecker pagePathQueryChecker = getPagePathQueryChecker(queryParams);
        PageRequest pageRequest;
        Sort sort = Sort.by(Sort.Direction.DESC , "score");
        pageRequest = PageRequest.of(pagePathQueryChecker.page(), pagePathQueryChecker.size(),sort);
        // : FIND ALL RELATED MEMBER'S TO THIS COMPLETION AND THEIR  RANKING ALSO
        Page<Ranking> rankings = this.rankingRepository.findAll(pageRequest);
        return rankings.map(PodiumMapper.INSTANCE::toDto);
    }





    @Override
    public PodiumCompetitionDto affichePodiumCompetition(String code, Map<String, String> queryParams) {
        updateRankingRank(code);
        PodiumCompetitionDto podiumCompetitionDto = new PodiumCompetitionDto();
        // : FIND THOSE SPECIFIC COMPETITION AND CHECK
        Optional<Competition> competition = this.competitionRepository.listerLesCompetitionParCode(code);
        if (competition.isEmpty()){
            throw new RuntimeException("THERE'S NO COMPETITION WITH THIS CODE");
        }

        podiumCompetitionDto.setCode(competition.get().getCode());
        podiumCompetitionDto.setDate(competition.get().getDate());
        podiumCompetitionDto.setLocation(competition.get().getLocation());
        podiumCompetitionDto.setStartTime(competition.get().getStartTime());
        podiumCompetitionDto.setEndTime(competition.get().getEndTime());
        AftasUtil.PagePathQueryChecker pagePathQueryChecker = getPagePathQueryChecker(queryParams);
        PageRequest pageRequest;
        // : SORT BY SCORE QUERY PARAMETER


        pageRequest = PageRequest.of(pagePathQueryChecker.page(), pagePathQueryChecker.size(), querySortPathExtracter(queryParams));
        // : FIND ALL RELATED MEMBER'S TO THIS COMPLETION AND THEIR  RANKING ALSO
        podiumCompetitionDto.setPodium(this.rankingRepository.findRankingsByCompetitionId(code,pageRequest).map(PodiumMapper.INSTANCE::toDto));

        return podiumCompetitionDto;
    }

    private void updateRankingRank(String codeCompetition) {
        List<Ranking> sortedRankings = this.rankingRepository.findRankingsByCompetitionId(codeCompetition,null).getContent();

        List<Ranking> finalSortedRankings = sortedRankings;
        sortedRankings = IntStream.range(0, sortedRankings.size())
                .mapToObj(index -> {
                    Ranking ranking = finalSortedRankings.get(index);
                    return ranking;
                })
                .sorted(Comparator.comparingInt(Ranking::getScore).reversed()).toList();

        List<Ranking> finalSortedRankings1 = sortedRankings;
        sortedRankings.forEach(ranking -> {
            //TODO : UPDATE RANKING RANK .
//            log.info("Member : "+ranking.getMember().getName() + " Score : "+ranking.getScore());
            ranking.setRank(finalSortedRankings1.indexOf(ranking)+1);
        });
        this.rankingRepository.saveAll(sortedRankings);
    }

    private Sort querySortPathExtracter(Map<String, String> queryParams) {
        Sort sort = null ;
        if (queryParams.containsKey("sort")){
            String sortParam = queryParams.get("sort");
            if (sortParam.equals("rank"))
            {
                sortParam = "rank";
            }
            else{
                sortParam = "score";
            }
            sort = Sort.by(Sort.Direction.ASC, sortParam);
        }
        if (queryParams.containsKey("desc")){
            String sortParam = queryParams.get("desc");
            if (sortParam.equals("rank"))
            {
                sortParam = "rank";
            }
            else{
                sortParam = "score";
            }
             sort = Sort.by(Sort.Direction.DESC, sortParam);
        }

        return sort;
    }


}
