package com.example.football_player_management.service;

import com.example.football_player_management.model.Player;

import java.util.List;

public interface IService<T> {
    List<T> getAll();

    void save(T s);

    void update(int id, T s);

    void remove(int id);

    T findById(int id);

    List<T> findByName(String name);

    List<Player> findAllById(List<Integer> ids);
}
