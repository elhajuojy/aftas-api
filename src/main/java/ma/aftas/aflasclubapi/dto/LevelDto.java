package ma.aftas.aflasclubapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LevelDto {
    private String code ;
    private String description;
    private Integer points;

}
