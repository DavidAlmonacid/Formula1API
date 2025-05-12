package com.example.formula1api;

import com.example.formula1api.driver.Driver;
import com.example.formula1api.driver.DriverRepository;
import com.example.formula1api.team.Team;
import com.example.formula1api.team.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        // Create and save teams
        Team ferrari = new Team("Ferrari", "Scuderia Ferrari HP");
        Team mclaren = new Team("McLaren", "McLaren Formula 1 Team");
        teamRepository.save(ferrari);
        teamRepository.save(mclaren);

        // Create and save drivers with the saved teams
        Driver driver1 = new Driver("Charles Leclerc", ferrari);
        Driver driver2 = new Driver("Oscar Piastri", mclaren);
        driverRepository.save(driver1);
        driverRepository.save(driver2);
    }
}
