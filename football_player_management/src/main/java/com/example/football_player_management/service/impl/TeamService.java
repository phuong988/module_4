package com.example.football_player_management.service.impl;

import com.example.football_player_management.model.Team;
import com.example.football_player_management.repository.TeamRepository;
import com.example.football_player_management.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamService implements ITeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
