package ma.aftas.aflasclubapi.web.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import ma.aftas.aflasclubapi.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findAllByNum(Integer num);
    Optional<Member> findByIdentityNumber(String identityNumber);



}
