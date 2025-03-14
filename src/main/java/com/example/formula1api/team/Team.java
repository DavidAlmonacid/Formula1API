package com.example.formula1api.team;

import com.example.formula1api.driver.Driver;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private List<Driver> drivers;

    // Constructors
    public Team() {
    }

    public Team(@NonNull String name) {
        this.name = name;
    }

}
