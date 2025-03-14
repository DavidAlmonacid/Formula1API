package com.example.formula1api.race;

import com.example.formula1api.driver.Driver;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate date;

    @ManyToMany
    @JoinTable(
            name = "race_participants",
            joinColumns = @JoinColumn(name = "race_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> participants;

}
