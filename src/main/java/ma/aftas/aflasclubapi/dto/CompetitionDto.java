package ma.aftas.aflasclubapi.dto;

import lombok.*;
import ma.aftas.aflasclubapi.enums.StatusCompeition;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@ToString
@Setter
public class CompetitionDto {
    private String code ;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants ;
    private String location;
    private Double amount;
    private StatusCompeition status ;
}
