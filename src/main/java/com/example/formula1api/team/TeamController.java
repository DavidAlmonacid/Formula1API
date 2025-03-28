package com.example.formula1api.team;

import com.example.formula1api.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/f1/teams")
public class TeamController {

    private final TeamService teamService;

    private TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    private ResponseEntity<Team> findById(@PathVariable Long id) {
        Team team = teamService.findById(id);

        if (team == null) {
            throw new NotFoundException("Team with id '" + id + "' was not found");
        }

        return ResponseEntity.ok(team);
    }

    @PostMapping
    private ResponseEntity<Team> save(@RequestBody Team team) throws URISyntaxException {
        Team savedTeam = teamService.save(team);
        URI newTeamLocation = new URI("/api/f1/teams/" + savedTeam.getId());

        return ResponseEntity.created(newTeamLocation).build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Team> update(@PathVariable Long id, @RequestBody Team team) {
        Team updatedTeam = teamService.update(id, team);

        if (updatedTeam == null) {
            throw new NotFoundException("Team with id '" + id + "' was not found");
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        teamService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
