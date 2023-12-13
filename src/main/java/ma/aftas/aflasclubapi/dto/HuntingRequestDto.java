package ma.aftas.aflasclubapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HuntingRequestDto {

    private Integer fishId;
    private String identityNumber;

}
