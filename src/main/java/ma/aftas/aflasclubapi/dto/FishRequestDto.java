package ma.aftas.aflasclubapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.aftas.aflasclubapi.entity.Level;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishRequestDto {
    private String Name;
    private Double averageWeight;
    @JsonProperty("level_id")
    private Integer LevelId ;
}
