package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import ma.aftas.aflasclubapi.enums.IdentityDocumentType;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member extends BaseEntity{

//    @NotNull(message = "Num unique number must not be empty or repeated ")
//    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer num;
    private String name;
    private String familyName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accessionDate;
    private String nationality   ;
    private String nationalityFlag;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    @Column(unique = true)
    private String identityNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Ranking",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )

    private Collection<Competition> competitions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private Collection<Hunting> huntings = new ArrayList<>();

}
