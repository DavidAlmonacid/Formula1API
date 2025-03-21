package com.example.formula1api.team;

import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Team save(Team newTeam) {
        return teamRepository.save(newTeam);
    }

    public Team update(Long id, Team team) {
        var existingTeam = teamRepository.findById(id).orElse(null);

        if (existingTeam == null) {
            return null;
        }

        existingTeam.setName(team.getName());
        existingTeam.setFullName(team.getFullName());

        return teamRepository.save(existingTeam);
    }
}
