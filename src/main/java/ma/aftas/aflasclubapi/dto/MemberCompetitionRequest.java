package ma.aftas.aflasclubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberCompetitionRequest {

//    private String membreNum;
    private String identityNumber ;
    private String codeCompetition;
}
