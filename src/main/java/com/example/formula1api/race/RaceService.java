package com.example.formula1api.race;

import com.example.formula1api.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public Race save(Race newRace) {
        return raceRepository.save(newRace);
    }

    public Race findById(Long id) {
        return raceRepository.findById(id).orElse(null);
    }

    public Race update(Long id, Race newRace) {
        var existingRace = raceRepository.findById(id).orElse(null);

        if (existingRace == null) {
            return null;
        }

        existingRace.setName(newRace.getName());
        existingRace.setDate(newRace.getDate());

        return raceRepository.save(existingRace);
    }

    public void deleteById(Long id) {
        if (!raceRepository.existsById(id)) {
            throw new NotFoundException("Race with id '" + id + "' was not found");
        }

        raceRepository.deleteById(id);
    }
}
