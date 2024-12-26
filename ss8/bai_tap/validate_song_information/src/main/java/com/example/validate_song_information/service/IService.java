package com.example.validate_song_information.service;

import com.example.validate_song_information.model.Song;

import java.util.List;

public interface IService<Song> {
    List<Song> getAllSongs();
    Song getSongById(int id);
    Song saveSong(Song song);
    void deleteSong(int id);
}
