package ma.aftas.aflasclubapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PodiumCompetitionDto {
    private String code ;
    private String status ;
    private LocalDateTime date ;
    private Page<PodiumDto> podium ;
}
