package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Fish  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String Name;
    private Double averageWeight;
    @ManyToOne(cascade = CascadeType.ALL)
    private Level level ;
    @OneToMany(mappedBy = "fish")
    private Collection<Hunting> huntings  = new ArrayList<>();

}
