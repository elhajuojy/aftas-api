package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public  class  RankingId implements Serializable {

        private static final long serialVersionUUID = 1L;
        @Column(name = "member_num")
        private Integer memberId;
        @Column(name = "competition_id")
        private Integer competitionId;

}