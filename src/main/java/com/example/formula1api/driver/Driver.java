package com.example.formula1api.driver;

import com.example.formula1api.race.Race;
import com.example.formula1api.team.Team;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany(mappedBy = "participants")
    private List<Race> races;

    // Constructors
    public Driver() {
    }

    public Driver(@NonNull String name, @NonNull Team team) {
        this.name = name;
        this.team = team;
    }

}
