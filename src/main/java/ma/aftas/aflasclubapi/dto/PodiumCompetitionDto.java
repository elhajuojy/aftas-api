package ma.aftas.aflasclubapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PodiumCompetitionDto {
    private String code ;
    private String status ;
    private LocalDate date ;
    private LocalTime startTime ;
    private LocalTime endTime ;
    private String location ;
    private Page<PodiumDto> podium ;
}
