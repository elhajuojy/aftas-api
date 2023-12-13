package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Competition;
import ma.aftas.aflasclubapi.entity.Member;
import ma.aftas.aflasclubapi.entity.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RankingRepository extends JpaRepository<Ranking, UUID> {

    // : OPTIONAL FIND ALL RANKING BY COMPETITION CODE
    @Query("SELECT U FROM Ranking U WHERE U.competition.code =:code")
    Page<Ranking> findRankingsByCompetitionId(@Param("code")String code, Pageable pageable);

    Optional<Ranking> findByCompetitionAndMember(Member member , Competition competition);

    @Query("SELECT U FROM Ranking U WHERE U.competition.code =:code AND U.member.id =:memberId")
    Optional<Ranking> findRankingsByCompetitionIdAndMemberId(@Param("code")String code,@Param("memberId") Integer memberId);


}
