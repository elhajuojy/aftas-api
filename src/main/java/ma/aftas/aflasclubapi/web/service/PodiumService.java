package ma.aftas.aflasclubapi.web.service;

import ma.aftas.aflasclubapi.dto.PodiumCompetitionDto;
import ma.aftas.aflasclubapi.dto.PodiumDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface PodiumService {
    public Page<PodiumDto> affichePodium(Map<String,String> queryParams);
    public PodiumCompetitionDto affichePodiumCompetition(String code , Map<String,String> queryParams);
}
