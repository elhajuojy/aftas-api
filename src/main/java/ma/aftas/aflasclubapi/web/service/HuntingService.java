package ma.aftas.aflasclubapi.web.service;


import ma.aftas.aflasclubapi.dto.HuntingRequestDto;
import ma.aftas.aflasclubapi.dto.HuntingResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface HuntingService {
    public HuntingResponseDto addNewHuntingToMember( String codeCompetition , HuntingRequestDto huntingRequestDto);

    HuntingResponseDto getHuntingByCompetitionId(String code);
}
