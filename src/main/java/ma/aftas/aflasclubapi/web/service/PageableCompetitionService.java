package ma.aftas.aflasclubapi.web.service;

import ma.aftas.aflasclubapi.dto.CompetitionDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PageableCompetitionService {
    public Page<CompetitionDto> ListerLesCompetition(Map<String, String> queryParams);
}
