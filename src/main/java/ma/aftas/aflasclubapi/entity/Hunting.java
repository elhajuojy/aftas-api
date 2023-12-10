package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hunting extends BaseEntity {
    private Integer idHunting ;
    private Integer numberOfFish;
    @ManyToOne()
    private Fish fish;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Competition competition;



}
