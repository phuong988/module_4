package com.example.football_player_management.service;

import com.example.football_player_management.model.Player;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPlayerService extends IService<Player> {
    Page<Player> findByName(String team, Integer page);
    void registerPlayer(int playerId);
    void unregisterPlayer(int playerId);
}
