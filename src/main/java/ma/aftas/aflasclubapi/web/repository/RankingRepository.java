package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RankingRepository extends JpaRepository<Ranking, UUID> {

}
