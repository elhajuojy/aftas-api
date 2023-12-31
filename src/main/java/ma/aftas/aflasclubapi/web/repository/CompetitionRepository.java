package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Competition;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@EnableJpaRepositories
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    @Query("SELECT U FROM Competition U WHERE  U.date < :localDate")
    Page<Competition> listerLesCompetitionFerme(@Param("localDate") LocalDate localDate, Pageable pageable);

    @Query("SELECT U FROM Competition U WHERE  U.date > :localDate")
    Page<Competition> listerLesCompetitionAvenir(@Param("localDate") LocalDate localDate, Pageable pageable);

    @Query("SELECT U FROM Competition U WHERE  U.date = :localDate")
    Page<Competition> listerLesCompetitionEncour(@Param("localDate") LocalDate localDate, Pageable pageable);

    @Query("SELECT U FROM Competition U WHERE  U.date = :localDate")
    Optional<Competition> listerLesCompetitionParDate(@Param("localDate") LocalDate localDate);

    @Query("SELECT U FROM Competition U WHERE  U.code = :code")
    Optional<Competition> listerLesCompetitionParCode(@Param("code") String code);

    Optional<Competition> findByCode( String code);

}
