package com.example.football_player_management.service.impl;

import com.example.football_player_management.exception.PlayerNotFoundException;
import com.example.football_player_management.model.Player;
import com.example.football_player_management.repository.PlayerRepository;
import com.example.football_player_management.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private PlayerRepository playerRepository;


    @Override
    public List<Player> getAll() {
        return playerRepository.findAll()  ;
    }

    @Override
    public void save(Player s) {
        playerRepository.save(s);
    }

    @Override
    public void update(int id, Player s) {
        Player existingPlayer = findById(id);
        if (existingPlayer == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        s.setId(id);
        playerRepository.save(s);
    }

    @Override
    public void remove(int id) {
        Player existingPlayer = findById(id);
        if (existingPlayer == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }

    @Override
    public Player findById(int id) {
        return playerRepository.findById(id).orElse(null)  ;
    }

    @Override
    public List<Player> findByName(String name) {

        return playerRepository.findAllByFullNameContainingIgnoreCase(name);
    }

    @Override
    public Page<Player> findByName(String team, Integer page) {
        return playerRepository.findAllByFullNameContainingIgnoreCase(team, PageRequest.of(page, 5));
    }
}
