package com.example.football_player_management.repository;

import com.example.football_player_management.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Integer> {
    List<Player> findAllByFullNameContainingIgnoreCase(String name);
    Page<Player> findAllByFullNameContainingIgnoreCase(String name, Pageable pageable);
}
