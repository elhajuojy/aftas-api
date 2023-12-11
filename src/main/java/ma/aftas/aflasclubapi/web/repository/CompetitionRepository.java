package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.UUID;
@EnableJpaRepositories
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    @Query("SELECT U FROM Competition U WHERE  U.date < :localDate")
    Page<Competition> listerLesCompetitionFerme(@Param("localDate")LocalDate localDate, Pageable pageable);

    @Query("SELECT U FROM Competition U WHERE  U.date > :localDate")
    Page<Competition> listerLesCompetitionAvenir(@Param("localDate") LocalDate localDate ,  Pageable pageable);

    @Query("SELECT U FROM Competition U WHERE  U.date = :localDate")
    Page<Competition> listerLesCompetitionEncour(@Param("localDate") LocalDate localDate ,  Pageable pageable);
}
