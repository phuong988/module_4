package com.example.validate_song_information.controller;

import com.example.validate_song_information.model.Song;
import com.example.validate_song_information.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService songService;

    @GetMapping
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.getAllSongs());
        return "song/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "song/form";
    }

    @PostMapping("/create")
    public String createSong(@Valid @ModelAttribute Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "song/form";
        }
        songService.saveSong(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Song song = songService.getSongById(id);
        if (song == null) {
            return "redirect:/songs";
        }
        model.addAttribute("song", song);
        return "song/form";
    }

    @PostMapping("/edit/{id}")
    public String updateSong(@PathVariable int id, @Valid @ModelAttribute Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "song/form";
        }
        song.setId(id);
        songService.saveSong(song);
        return "redirect:/songs";
    }

    @PostMapping("/delete/{id}")
    public String deleteSong(@PathVariable int id) {
        songService.deleteSong(id);
        return "redirect:/songs";
    }
}
