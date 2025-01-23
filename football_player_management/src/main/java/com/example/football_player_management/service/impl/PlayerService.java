package com.example.football_player_management.service.impl;

import com.example.football_player_management.exception.PlayerNotFoundException;
import com.example.football_player_management.model.Player;
import com.example.football_player_management.model.Team;
import com.example.football_player_management.repository.PlayerRepository;
import com.example.football_player_management.repository.TeamRepository;
import com.example.football_player_management.service.IPlayerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService implements IPlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

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
        return playerRepository.findAllByFullNameContainingIgnoreCase(team, PageRequest.of(page, 6));
    }

    @Override
    public void registerPlayer(int playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Không tìm thấy cầu thủ."));
        Team team = player.getTeam();

        if (team.getRegisteredPlayers() >= 11) {
            throw new IllegalStateException("Số lượng cầu thủ đã đăng ký không được vượt quá 11.");
        }

        if (!"Dự bị".equals(player.getStatus())) {
            throw new IllegalStateException("Cầu thủ này đã được đăng ký.");
        }

        player.setStatus("Đã đăng ký");
        team.setRegisteredPlayers(team.getRegisteredPlayers() + 1);
        teamRepository.save(team);
        playerRepository.save(player);
    }

    @Override
    public void unregisterPlayer(int playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Không tìm thấy cầu thủ."));
        Team team = player.getTeam();

        if (!"Đã đăng ký".equals(player.getStatus())) {
            throw new IllegalStateException("Cầu thủ này chưa được đăng ký.");
        }

        player.setStatus("Dự bị");
        team.setRegisteredPlayers(team.getRegisteredPlayers() - 1);
        teamRepository.save(team);
        playerRepository.save(player);
    }

    @Override
    public List<Player> findAllById(List<Integer> ids) {
        return playerRepository.findAllById(ids);
    }


}
