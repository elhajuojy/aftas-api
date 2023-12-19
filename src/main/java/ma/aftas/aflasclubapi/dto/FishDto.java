package ma.aftas.aflasclubapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishDto {
    private Integer id;
    private String Name;
    private Double averageWeight;
}
