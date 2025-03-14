package com.example.formula1api.team;

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

        if (team != null) {
            return ResponseEntity.ok(team);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity<Team> save(@RequestBody Team team) throws URISyntaxException {
        Team savedTeam = teamService.save(team);
        URI newTeamLocation = new URI("/api/f1/teams/" + savedTeam.getId());

        return ResponseEntity.created(newTeamLocation).build();
    }

}
