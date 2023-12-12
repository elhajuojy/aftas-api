package ma.aftas.aflasclubapi.web.service.impl;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import ma.aftas.aflasclubapi.dto.*;
import ma.aftas.aflasclubapi.entity.Competition;
import ma.aftas.aflasclubapi.exception.business.AlreadyExistsException;
import ma.aftas.aflasclubapi.exception.business.BadRequestException;
import ma.aftas.aflasclubapi.mappers.CompetitionMapper;
import ma.aftas.aflasclubapi.web.repository.CompetitionRepository;
import ma.aftas.aflasclubapi.web.service.CompetitionService;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Log4j2
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;
    private Faker faker;

    Logger logger = org.slf4j.LoggerFactory.getLogger(CompetitionServiceImpl.class);

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = CompetitionMapper.INSTANCE;
        this.faker = new Faker();

    }

    @Override
    public CompetitionDto ajouterCompetition(CompetitionRequestDto competitionRequestDto) {
//        List<String> errors = new ArrayList<>();
        ajouterCompetitionValidationDto(competitionRequestDto);

        // : CHECK IF THERE'S ALREADY EVENT IN THE SAME DATE IF EXIT'S THROW ALREADY RESERVED EXCEPTION
        if(this.competitionRepository.listerLesCompetitionParDate(competitionRequestDto.getDate()).isPresent()){
            throw  new AlreadyExistsException("THERE'S ALREADY COMPETITION PLANNED IN THIS DATE THAT YOU HAVE PROVIDED ");
        }

        Competition competition  = this.competitionMapper.toEntity(competitionRequestDto);
        //Todo:  code pattern -> ims-22-12-23
        String code = competition.getLocation() + competition.getDate();
        competition.setCode(code);
        competition.setNumberOfParticipants(0);
        logger.info("ajouter competition  code "+competition.getCode());



        return this.competitionMapper.toDto(this.competitionRepository.save(competition));


    }

    private void ajouterCompetitionValidationDto(CompetitionRequestDto competitionRequestDto) {
        Map<String,String> errors = new HashMap<>();
        if (competitionRequestDto != null){
            if (competitionRequestDto.getDate() == null){
                errors.put("date","Date de competition est obligatoire");
            }
            if (competitionRequestDto.getStartTime() == null){
                errors.put("startTime","Heure de debut de competition est obligatoire");
            }
            if (competitionRequestDto.getEndTime() == null){
                errors.put("endTime","Heure de fin de competition est obligatoire");
            }
            if (competitionRequestDto.getLocation() == null || competitionRequestDto.getLocation().isEmpty()){
                errors.put("location","Location de competition est obligatoire");
            }
            if (competitionRequestDto.getAmount() == null){
                errors.put("amount","Montant de competition est obligatoire");
            }
            if (!errors.isEmpty()){
                throw new BadRequestException("Input validation exception's ",errors);
            }
        }
    }

    @Override
    public MemberCompetitionResponse inscriptionMembreDansCompetition(MemberCompetitionRequest memberCompetitionRequest) {
        //TODO : inscription Member Dans une Competition (check si membre exists ou non  )

        return null;
    }

    @Override
    public Page<CompetitionDto> ListerLesCompetition(Map<String, String> queryParams) {
        PageRequest pageRequest ;
        int page;
        int size;
        if(queryParams.containsKey("page") && queryParams.containsKey("size")){
            page = Integer.parseInt(queryParams.get("page"));
            size = Integer.parseInt(queryParams.get("size"));
        }else{
            page = 0;
            size = 10;
        }
        pageRequest = PageRequest.of(page , size);

        if (queryParams.containsKey("status")) {
            return getLesCompetitionParStatus(queryParams.get("status"), pageRequest);
        }
        Page<Competition> competitions = this.competitionRepository.findAll(pageRequest);
        return competitions.map(CompetitionMapper.INSTANCE::toDto);
    }

    private Page<CompetitionDto> getLesCompetitionParStatus(String status, PageRequest pageRequest) {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());

            switch (status){
                case "encour" -> {
                    return this.competitionRepository.listerLesCompetitionEncour(today, pageRequest).map(CompetitionMapper.INSTANCE::toDto);
                }
                case "avenir"->{
                    return this.competitionRepository.listerLesCompetitionAvenir(today,pageRequest).map(CompetitionMapper.INSTANCE::toDto);
                }
                case "ferme"->{
                   return this.competitionRepository.listerLesCompetitionFerme(today, pageRequest).map(CompetitionMapper.INSTANCE::toDto);
                }
                default -> throw new  IllegalArgumentException(status);

            }

    }

    @Override
    public Page<PodiumDto> affichePodium(Map<String, String> queryParams) {
        //TODO: AFFICHE PODUIM
        return null;
    }

    @Override
    public PodiumCompetitionDto affichePodiumCompetition(String code, Map<String, String> queryParams) {
        PodiumCompetitionDto podiumCompetitionDto = new PodiumCompetitionDto();
        podiumCompetitionDto.setCode(code);
        //TODO : FIND ALL RELATED MEMBER'S TO THIS COMPLETION AND THEIR  RANKING ALSO
        return podiumCompetitionDto;
    }


}
