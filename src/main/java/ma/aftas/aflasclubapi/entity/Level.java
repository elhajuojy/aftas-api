package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.Entity;
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
public class Level extends BaseEntity{
    private String code ;
    private String description;
    private Integer points;
    @OneToMany(mappedBy ="member" )
    Collection<Level> levels = new ArrayList<>();
}
