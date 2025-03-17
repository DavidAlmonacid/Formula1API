package com.example.formula1api.team;

import com.example.formula1api.driver.Driver;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Entity
@Table(name = "teams")
@JsonIgnoreProperties({"drivers"})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Team name is required")
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Driver> drivers;

    // Constructors
    public Team() {
    }

    public Team(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
