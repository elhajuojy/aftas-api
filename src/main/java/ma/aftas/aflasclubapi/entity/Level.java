package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Level extends BaseEntity{
    private String code ;
    private String description;
    private Integer points;
    @OneToMany(mappedBy ="level")
    Collection<Fish> fishes = new ArrayList<>();
}
