package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LevelRepository extends JpaRepository<Level, UUID> {
}
