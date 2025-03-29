package com.example.formula1api.race;

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
}
