package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Competition extends BaseEntity{
    private String code ;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer numberOfParticipants ;
    private String location;
    private Double amount;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ranking",
    joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "member_num")
    )
    private Collection<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "competition")
    private Collection<Hunting> huntings = new ArrayList<>();

    public Integer getNumberOfParticipants() {
        return this.getMembers().size();
    }


}
