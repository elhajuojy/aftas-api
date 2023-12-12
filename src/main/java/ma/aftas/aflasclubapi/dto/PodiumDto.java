package ma.aftas.aflasclubapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.aftas.aflasclubapi.entity.Ranking;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PodiumDto {
    private Integer rank ;
    private Integer score ;
    private Integer num;
    private String name;
    private String familyName;
    private String nationality;
    private String nationalityFlag;








}
