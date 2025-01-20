package com.example.football_player_management.exception;

public class PlayerNotFoundException extends RuntimeException  {
    public PlayerNotFoundException(String message) {
        super(message);
    }
}
