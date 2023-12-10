package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.aftas.aflasclubapi.enums.IdentityDocumentType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member extends BaseEntity{

    private Integer num;
    private String name;
    private String familyName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime accessionDate;
    private String nationality   ;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;
    @ManyToMany
    @JoinTable(name = "Ranking",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "competition_id")
    )
    private Collection<Competition> competitions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private Collection<Hunting> huntings = new ArrayList<>();








}
