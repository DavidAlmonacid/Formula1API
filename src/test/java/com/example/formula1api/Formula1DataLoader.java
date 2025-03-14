package com.example.formula1api;

import com.example.formula1api.driver.Driver;
import com.example.formula1api.driver.DriverRepository;
import com.example.formula1api.team.Team;
import com.example.formula1api.team.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Formula1DataLoader implements CommandLineRunner {

    private final DriverRepository driverRepository;
    private final TeamRepository teamRepository;

    private Formula1DataLoader(DriverRepository driverRepository, TeamRepository teamRepository) {
        this.driverRepository = driverRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public void run(String... args) {
        List<Team> teams = List.of(
                new Team("Williams"),
                new Team("McLaren"),
                new Team("Ferrari"),
                new Team("Mercedes"),
                new Team("Red Bull Racing"),
                new Team("Aston Martin"),
                new Team("Alpine"),
                new Team("AlphaTauri"),
                new Team("Alfa Romeo Racing"),
                new Team("Haas F1 Team")
        );
        teamRepository.saveAll(teams);

        driverRepository.saveAll(List.of(
                new Driver("George Russell", teams.get(0)),
                new Driver("Nicholas Latifi", teams.get(0)),
                new Driver("Lando Norris", teams.get(1)),
                new Driver("Daniel Ricciardo", teams.get(1)),
                new Driver("Charles Leclerc", teams.get(2)),
                new Driver("Carlos Sainz", teams.get(2)),
                new Driver("Lewis Hamilton", teams.get(3)),
                new Driver("Valtteri Bottas", teams.get(3)),
                new Driver("Max Verstappen", teams.get(4)),
                new Driver("Sergio Perez", teams.get(4)),
                new Driver("Sebastian Vettel", teams.get(5)),
                new Driver("Lance Stroll", teams.get(5)),
                new Driver("Fernando Alonso", teams.get(6)),
                new Driver("Esteban Ocon", teams.get(6)),
                new Driver("Pierre Gasly", teams.get(7)),
                new Driver("Yuki Tsunoda", teams.get(7)),
                new Driver("Kimi Raikkonen", teams.get(8)),
                new Driver("Antonio Giovinazzi", teams.get(8)),
                new Driver("Mick Schumacher", teams.get(9)),
                new Driver("Nikita Mazepin", teams.get(9))
        ));
    }

}
