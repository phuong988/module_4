package com.example.football_player_management.controller;

import com.example.football_player_management.exception.PlayerNotFoundException;
import com.example.football_player_management.model.Player;
import com.example.football_player_management.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerRestController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping("")
    public ResponseEntity<List<Player>> getAllPlayers(@RequestParam(defaultValue = "") String name) {
        List<Player> players = playerService.findByName(name, 0).getContent();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerDetails(@PathVariable int id) {
        Player player = playerService.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        return ResponseEntity.ok(player);
    }

    @PostMapping("")
    public ResponseEntity<Player> addPlayer(@RequestBody @Validated Player player, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        playerService.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int id, @RequestBody @Validated Player player, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Player existingPlayer = playerService.findById(id);
        if (existingPlayer == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        playerService.update(id, player);
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable int id) {
        Player player = playerService.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        playerService.remove(id);
        return ResponseEntity.noContent().build();
    }
}
