package ma.aftas.aflasclubapi.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.aftas.aflasclubapi.enums.IdentityDocumentType;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberResponseDto {
    private Integer num;
    @NotNull
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
}
