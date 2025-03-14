package com.example.formula1api.driver;

import com.example.formula1api.race.Race;
import com.example.formula1api.team.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Name is required")
    private String name;

    @NonNull
    @JsonIgnoreProperties("drivers")
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

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
