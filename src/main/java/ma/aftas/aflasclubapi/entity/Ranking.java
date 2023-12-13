package ma.aftas.aflasclubapi.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "RANKING")
public class Ranking  {

    @EmbeddedId
    private RankingId id ;
    @ColumnDefault("0")
    private Integer rank ;
    @ColumnDefault("0")
    private Integer score  ;
    @ManyToOne
    @MapsId("memberId")
    private Member member;
    @ManyToOne
    @MapsId("competitionId")
    private Competition competition;




}



