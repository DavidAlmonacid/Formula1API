package com.example.formula1api.team;

import com.example.formula1api.driver.Driver;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Entity
@Table(name = "teams")
@JsonIgnoreProperties({ "drivers" })
@JsonDeserialize(using = TeamDeserializer.class)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Team 'name' is required")
    @Column(unique = true)
    private String name;

    @NonNull
    @NotBlank(message = "Team 'fullName' is required")
    @Column(unique = true)
    private String fullName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Driver> drivers;

    // Constructors
    public Team() {
    }

    public Team(@NonNull String name, @NonNull String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    // toString
    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
