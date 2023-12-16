package ma.aftas.aflasclubapi.web.service.impl;

import ma.aftas.aflasclubapi.entity.Hunting;
import ma.aftas.aflasclubapi.entity.Ranking;
import ma.aftas.aflasclubapi.web.repository.HuntingRepository;
import ma.aftas.aflasclubapi.web.repository.RankingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HuntingServiceImplTest {

    @Mock
    private HuntingRepository huntingRepository ;
    @Mock
    RankingRepository rankingRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addNewHuntingToMember() {
        //TODO : TEST DIFFERENT LEVEL OF TO BE INSERTED IN
        Hunting hunting = new Hunting();
        Ranking ranking = new Ranking();
        ranking.setScore(12);
        Integer newScoreBasedOnFishLevel = 20;
        when(this.huntingRepository.save(hunting)).thenReturn(hunting);
        assertEquals(ranking.getScore()+newScoreBasedOnFishLevel,32);
        assert  this.huntingRepository.save(hunting) == hunting;
        assertTrue(this.huntingRepository.save(hunting)!=null);
        //TODO : TEST ADDING SCORE COUNTING AND RANK ALSO

    }

    @Test
    void rankMembersInCompetition(){
        // : I NEED RANK MEMBER'S THAT ATTEND CURRENT  COMPETITION THEN
        List<Ranking> rankings = Arrays.asList(1, 2, 3,4).stream().map((rank) -> {
            Ranking ranking = new Ranking();
            ranking.setScore(rank * 10);
            return ranking;
        }).toList();
        assertNotNull(rankings);
        rankings.get(1).setScore(10);
        assertEquals(rankings.get(0).getScore(), rankings.get(1).getScore());


        List<Ranking> sortedList = IntStream.range(0, rankings.size())
                .mapToObj(index -> {
                    Ranking ranking = rankings.get(index);
                    return ranking;
                })
                .sorted(Comparator.comparingInt(Ranking::getScore).reversed()).toList();

        assertEquals(10, rankings.get(0).getScore());
        assertEquals(40,sortedList.get(0).getScore());





        AtomicInteger index = new AtomicInteger(1);
        sortedList.forEach((element)->{
            //TODO: TEST FOREACH sortedlist
            element.setRank(index.getAndIncrement());

        });




        assertNotNull(rankings);
        assertEquals(1,sortedList.get(0).getRank());
        assertEquals(2,sortedList.get(1).getRank());
        // BASED ON THEIR SCORE WHICH I NEED TO CALCULATE IT FROM THE HUNTING FOR EACH MEMBER
        


        // Now, assuming Ranking has a method to get the score, you can assert like this


        //TODO : what if there's to many score's with the same
        // value you need to rank them with the same rank





    }


    @Test
    void updateRankingScore() {
    }

    @Test
    void updateRankingRank() {
    }
}

class RankComparator implements Comparator<Ranking>{
    @Override
    public int compare(Ranking o1, Ranking o2) {
        return o1.getScore() - o1.getScore();
    }
}