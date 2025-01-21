package com.example.football_player_management.repository;

import com.example.football_player_management.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Integer> {
}
