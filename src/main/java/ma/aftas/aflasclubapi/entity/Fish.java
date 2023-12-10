package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;


@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fish extends BaseEntity {
    private String Name;
    private Double averageWeight;
    @ManyToOne(cascade = CascadeType.ALL)
    private Level level ;
    @OneToMany(mappedBy = "fish")
    private Collection<Hunting> huntings  = new ArrayList<>();



}
