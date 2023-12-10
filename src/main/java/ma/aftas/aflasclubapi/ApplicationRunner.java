package ma.aftas.aflasclubapi;

import com.github.javafaker.Faker;
import ma.aftas.aflasclubapi.entity.Member;
import ma.aftas.aflasclubapi.enums.IdentityDocumentType;
import ma.aftas.aflasclubapi.web.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private MemberRepository memberRepository ;

    public ApplicationRunner(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<Integer , IdentityDocumentType> identityDocumentTypeMap = Map.of(
                1,IdentityDocumentType.CIN,
                2,IdentityDocumentType.CATRE_PRESIDENCE,
                3,IdentityDocumentType.PASSPORT
        );

        Faker faker = new Faker();
        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Collection<Member> members = Stream.of(faker.name(),faker.name(),faker.name()).map(
                name -> {
                    //TODO : SAVE THOSE TO DATABASE FOR TEST
                    Member member = new Member();
                    member.setName(name.firstName());
                    member.setId(UUID.randomUUID());
                    member.setFamilyName(name.lastName());
                    member.setNationalityFlag(faker.country().flag());
                    member.setNationality(faker.country().name());
                    member.setNum(faker.number().randomDigit());
                    member.setIdentityDocumentType(
                        identityDocumentTypeMap.get(faker.number().numberBetween(1,3))
                    );
                    member.setIdentityNumber(faker.idNumber().valid());
                    member.setVersion("1");
                    member.setAccessionDate(LocalDateTime.now());
                    return this.memberRepository.save(member);

                }
        ).toList();

    }
}
