package ma.aftas.aflasclubapi.web.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import ma.aftas.aflasclubapi.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Page<Member> findAllByNum(Integer num, PageRequest pageRequest);
    Optional<Member> findAllByNum(Integer num);
    Page<Member> findByIdentityNumber(String identityNumber,PageRequest pageRequest);
    Optional<Member> findByIdentityNumber(String identityNumber);
    Page<Member> findByFamilyName(String familyName, PageRequest pageRequest);
    Optional<Member> findByFamilyName(String familyName );

    Page<Member> findByName(String name, PageRequest pageRequest);
    Optional<Member> findByName(String name);

}
