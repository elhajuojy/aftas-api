package ma.aftas.aflasclubapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberCompetitionResponse {
    MemberDto memberDto  ;

    CompetitionDto competitionDto ;
}
