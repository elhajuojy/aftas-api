package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Hunting extends BaseEntity {
    private Integer idHunting ;
    private Integer numberOfFish;
    @ManyToOne
    private Fish fish;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Competition competition;



}
