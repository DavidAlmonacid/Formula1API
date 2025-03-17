package com.example.formula1api.driver;

import com.example.formula1api.team.TeamRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final TeamRepository teamRepository;

    private DriverService(DriverRepository driverRepository, TeamRepository teamRepository) {
        this.driverRepository = driverRepository;
        this.teamRepository = teamRepository;
    }

    public List<Driver> findAll() {
        return (List<Driver>) driverRepository.findAll();
    }

    @Nullable
    public Driver findById(Long id) {
        return driverRepository.findById(id).orElse(null);
    }

    public Driver save(Driver newDriver) {
        var team = newDriver.getTeam();

        if (team.getId() == null) {
            // Persist the team first so that it is not transient.
            team = teamRepository.save(team);
            newDriver.setTeam(team);
        }

        return driverRepository.save(newDriver);
    }

    @Nullable
    public Driver update(Long id, Driver driver) {
        var existingDriver = findById(id);

        if (existingDriver == null) {
            return null;
        }

        // Check if the team entity is new and persist it if necessary.
        var team = driver.getTeam();

        if (team.getId() == null) {
            team = teamRepository.save(team);
        }

        existingDriver.setName(driver.getName());
        existingDriver.setTeam(team);

        return save(existingDriver);
    }

}
