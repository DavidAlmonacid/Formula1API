package com.example.formula1api.driver;

import com.example.formula1api.exceptions.NotFoundException;
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
        var existingTeam = teamRepository.findByName(team.getName()); // Check if a team with the same name already exists

        newDriver.setTeam(existingTeam.orElseGet(() -> teamRepository.save(team)));

        return driverRepository.save(newDriver);
    }

    @Nullable
    public Driver update(Long id, Driver driver) {
        var existingDriver = driverRepository.findById(id).orElse(null);

        if (existingDriver == null) {
            return null;
        }

        var team = driver.getTeam();
        var existingTeam = teamRepository.findByName(team.getName()); // Check if a team with the same name already exists

        existingDriver.setTeam(existingTeam.orElseGet(() -> teamRepository.save(team)));
        existingDriver.setName(driver.getName());

        return driverRepository.save(existingDriver);
    }

    public void deleteById(Long id) {
        if (!driverRepository.existsById(id)) {
            throw new NotFoundException("Driver with id '" + id + "' was not found");
        }

        driverRepository.deleteById(id);
    }
}
