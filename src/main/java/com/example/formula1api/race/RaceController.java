package com.example.formula1api.race;

import com.example.formula1api.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/f1/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping
    private ResponseEntity<Void> save(@RequestBody Race race) throws URISyntaxException {
        var savedRace = raceService.save(race);
        var location = new URI("/api/f1/races/" + savedRace.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Race> findById(@PathVariable Long id) {
        var race = raceService.findById(id);

        if (race == null) {
            throw new NotFoundException("Race with id '" + id + "' was not found");
        }

        return ResponseEntity.ok(race);
    }
}
