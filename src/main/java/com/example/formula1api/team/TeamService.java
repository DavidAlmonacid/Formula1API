package com.example.formula1api.team;

import com.example.formula1api.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return (List<Team>) teamRepository.findAll();
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

    public void deleteById(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new NotFoundException("Team with id '" + id + "' was not found");
        }

        teamRepository.deleteById(id);
    }
}
