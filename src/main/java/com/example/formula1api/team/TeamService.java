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
}
