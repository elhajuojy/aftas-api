package ma.aftas.aflasclubapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.aftas.aflasclubapi.enums.IdentityDocumentType;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer num;
    private String name;
    @NotNull
    private String familyName;
    @NotNull
    private String nationality ;
    @NotNull
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @NotNull
    private String identityNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime accessionDate;
}
