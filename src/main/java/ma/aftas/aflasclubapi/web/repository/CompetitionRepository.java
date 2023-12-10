package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<Competition, UUID> {
}
