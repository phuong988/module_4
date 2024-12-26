package com.example.validate_song_information.repository;

import com.example.validate_song_information.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
