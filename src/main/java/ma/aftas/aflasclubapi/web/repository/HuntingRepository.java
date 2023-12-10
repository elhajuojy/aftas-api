package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HuntingRepository extends JpaRepository<Hunting, UUID> {

}
