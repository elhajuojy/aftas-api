package ma.aftas.aflasclubapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompetitionRequestDto {
    @NotNull(message = "Date de competition est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotNull(message = "Heure de debut de competition est obligatoire")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @NotNull(message = "Heure de fin de competition est obligatoire")
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "")
    private LocalTime endTime;
    @NotNull(message = "Location de competition est obligatoire")
    private String location;
    @NotNull(message = "Montant de competition est obligatoire")
    private Double amount;

}
