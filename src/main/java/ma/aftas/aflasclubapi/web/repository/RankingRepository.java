package ma.aftas.aflasclubapi.web.repository;

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

}
