package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fish extends BaseEntity {
    private String Name;
    private Double averageWeight;
    @ManyToOne(cascade = CascadeType.ALL)
    private Level level ;
    @OneToMany(mappedBy = "")
    private Collection<Hunting> huntings  = new ArrayList<>();



}
