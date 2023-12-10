package ma.aftas.aflasclubapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Competition extends BaseEntity{
    private String code ;
    private Date date;
    private Time startTime;
    private Time endTime;
    private Integer numberOfParticipants ;
    private String location;
    private Double amount;
    @ManyToMany
    @JoinTable(name = "ranking",
    joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private Collection<Member> members = new ArrayList<>();
    @OneToMany(mappedBy = "competition")
    private Collection<Hunting> huntings = new ArrayList<>();







}
