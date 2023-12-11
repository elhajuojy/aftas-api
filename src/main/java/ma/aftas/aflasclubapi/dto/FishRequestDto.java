package ma.aftas.aflasclubapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishRequestDto {
    private String Name;
    private Double averageWeight;
    @JsonProperty("Level")
    private LevelDto levelDto ;
}
