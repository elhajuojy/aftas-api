package ma.aftas.aflasclubapi.web.service;

import ma.aftas.aflasclubapi.dto.*;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface CompetitionService extends PageableCompetitionService{

    public CompetitionDto ajouterCompetition(CompetitionRequestDto competitionRequestDto);
    public MemberCompetitionResponse inscriptionMembreDansCompetition(MemberCompetitionRequest memberCompetitionRequest);
    CompetitionDto getCompetitionByCode(String code);
}

