package com.example.formula1api.race;

import com.example.formula1api.driver.Driver;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank(message = "Race 'name' is required")
    private String name;

    @NonNull
    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "race_participants",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> participants;

    // Constructors
    public Race() {
    }

    public Race(@NonNull String name, @NonNull LocalDate date) {
        this.name = name;
        this.date = date;
    }
}
