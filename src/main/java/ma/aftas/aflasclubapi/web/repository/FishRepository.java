package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FishRepository extends JpaRepository<Fish, UUID> {
}
