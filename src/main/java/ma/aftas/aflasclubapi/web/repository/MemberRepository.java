package ma.aftas.aflasclubapi.web.repository;

import ma.aftas.aflasclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {

}
