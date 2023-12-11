package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class  RankingId implements Serializable {

        private static final long serialVersionUUID = 1L;
        @Column(name = "member_id")
        private UUID memberId;
        @Column(name = "competition_id")
        private UUID competitionId;

    }