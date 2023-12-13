package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Level extends BaseEntity{
    private String code ;
    private String description;
    private Integer points;
    @OneToMany(mappedBy ="level",cascade = CascadeType.ALL)
    Collection<Fish> fishes = new ArrayList<>();
}
