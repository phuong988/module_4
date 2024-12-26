package com.example.validate_song_information.service.impl;

import com.example.validate_song_information.model.Song;
import com.example.validate_song_information.repository.SongRepository;
import com.example.validate_song_information.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongService implements ISongService {
    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song getSongById(int id) {
        return songRepository.findById(id).orElse(null);
    }

    @Override
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void deleteSong(int id) {
        songRepository.deleteById(id);
    }
}
