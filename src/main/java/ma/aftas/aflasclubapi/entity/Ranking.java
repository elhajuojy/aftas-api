package ma.aftas.aflasclubapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(schema = "Ranking")
public class Ranking extends BaseEntity{

    @EmbeddedId
    private RankingId id = new RankingId();
    private Integer rank ;
    private Integer score ;

    @ManyToOne
    @MapsId("memberId")
    private Member member;
    @ManyToOne
    @MapsId("competitionId")
    private Competition competition;

    @Embeddable
    @Data
    public static class RankingId implements Serializable {

        private static final long serialVersionUUID = 1L;
        @Column(name = "member_id")
        private UUID memberId;
        @Column(name = "competition_id")
        private UUID competitionId;

        public RankingId(UUID memberId, UUID competitionId) {
            this.memberId = memberId;
            this.competitionId = competitionId;
        }

        public RankingId() {
        }
    }

}

