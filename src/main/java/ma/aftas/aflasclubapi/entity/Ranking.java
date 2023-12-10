package ma.aftas.aflasclubapi.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "RANKING")
public class Ranking  {

    @EmbeddedId
    private RankingId id ;
    private Integer rank ;
    private Integer score ;

    @ManyToOne
    @MapsId("memberId")
    private Member member;
    @ManyToOne
    @MapsId("competitionId")
    private Competition competition;



}


