package ma.aftas.aflasclubapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberCompetitionResponse {
    @JsonProperty("member")
    MemberDto memberDto  ;
    @JsonProperty("competition")
    CompetitionDto competitionDto ;
}
