package ma.aftas.aflasclubapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HuntingRequestDto {

    private Integer fishId;
    private Integer num;
    @Min(1) @Max(100)
    private Double weight;
    @Min(1) @Max(100)
    private int number_of_fish;

}
