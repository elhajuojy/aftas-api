package ma.aftas.aflasclubapi;

import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import ma.aftas.aflasclubapi.entity.Competition;
import ma.aftas.aflasclubapi.entity.Fish;
import ma.aftas.aflasclubapi.entity.Member;
import ma.aftas.aflasclubapi.enums.IdentityDocumentType;
import ma.aftas.aflasclubapi.web.repository.CompetitionRepository;
import ma.aftas.aflasclubapi.web.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

@Component
@Log4j2
public class ApplicationRunner implements CommandLineRunner {

    private MemberRepository memberRepository ;
    private CompetitionRepository competitionRepository ;
    Faker faker ;
    Map<String, String> moroccoCityCodes = new HashMap<>();

    // Add city codes and names to the map


    public ApplicationRunner(
            MemberRepository memberRepository,
            CompetitionRepository competitionRepository
    ) {
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        Map<Integer , IdentityDocumentType> identityDocumentTypeMap = Map.of(
                1,IdentityDocumentType.CIN,
                2,IdentityDocumentType.CATRE_PRESIDENCE,
                3,IdentityDocumentType.PASSPORT
        );
        moroccoCityCodes.put("CAS", "Casablanca");
        moroccoCityCodes.put("RAB", "Rabat");
        moroccoCityCodes.put("MAR", "Marrakech");
        moroccoCityCodes.put("FES", "Fes");
        moroccoCityCodes.put("TNG", "Tangier");
        moroccoCityCodes.put("AGA", "Agadir");
        moroccoCityCodes.put("MEK", "Meknes");
        moroccoCityCodes.put("OUJ", "Oujda");
        moroccoCityCodes.put("KEN", "Kenitra");
        moroccoCityCodes.put("ESS", "Essaouira");
        moroccoCityCodes.put("FEZ", "Fez");
        moroccoCityCodes.put("NDR", "Nador");
        moroccoCityCodes.put("EJD", "El Jadida");
        moroccoCityCodes.put("TET", "Tetouan");
        moroccoCityCodes.put("OUA", "Ouarzazate");


        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Collection<Competition> competitions = fakeCompetitions() ;
        Collection<Member> members = Stream.of(faker.name(),faker.name(),faker.name()).map(
                name -> {
                    // : SAVE THOSE TO DATABASE FOR TEST
                    //MEMBER INFORMATION
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
                    // GETTING COMPETITION WHERE WE CAN REGISTER
                    member.setCompetitions(competitions);
                    return this.memberRepository.save(member);
                }
        ).toList();


    }

    private List<Competition> fakeCompetitions() {
        return Stream.of(faker.university().name(),faker.university().name()).map((something)->{
            Competition competition = new Competition();
            competition.setAmount(50000.00);
            competition.setId(UUID.randomUUID());
            competition.setDate(LocalDate.now().plusDays(5));
            competition.setStartTime(LocalTime.parse("08:00"));
            competition.setEndTime(LocalTime.parse("17:00"));
            competition.setNumberOfParticipants(competition.getNumberOfParticipants());
            competition.setLocation(moroccoCityCodes.get("NDR"));
            competition.setCode(faker.code().gtin8());
            return this.competitionRepository.save(competition);
        }).toList();
    }


    private List<Fish> fakeFishesStore(){
        return null;
    }
}
