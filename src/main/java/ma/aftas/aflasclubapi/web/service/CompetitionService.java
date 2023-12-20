package ma.aftas.aflasclubapi.web.service;

import ma.aftas.aflasclubapi.dto.*;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface CompetitionService {

    public CompetitionDto ajouterCompetition(CompetitionRequestDto competitionRequestDto);
    public MemberCompetitionResponse inscriptionMembreDansCompetition(MemberCompetitionRequest memberCompetitionRequest);
    public Page<CompetitionDto> ListerLesCompetition(Map<String, String> queryParams);
    CompetitionDto getCompetitionByCode(String code);
}

