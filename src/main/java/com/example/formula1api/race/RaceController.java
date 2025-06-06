package com.example.formula1api.race;

import com.example.formula1api.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/f1/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    private ResponseEntity<List<Race>> findAll() {
        return ResponseEntity.ok(raceService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Race> findById(@PathVariable Long id) {
        var race = raceService.findById(id);

        if (race == null) {
            throw new NotFoundException("Race with id '" + id + "' was not found");
        }

        return ResponseEntity.ok(race);
    }

    @PostMapping
    private ResponseEntity<Void> save(@RequestBody Race race) throws URISyntaxException {
        var savedRace = raceService.save(race);
        var location = new URI("/api/f1/races/" + savedRace.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Race race) {
        var updatedRace = raceService.update(id, race);

        if (updatedRace == null) {
            throw new NotFoundException("Race with id '" + id + "' was not found");
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        raceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
