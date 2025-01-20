package com.example.football_player_management.controller;

import com.example.football_player_management.exception.PlayerNotFoundException;
import com.example.football_player_management.model.Player;
import com.example.football_player_management.service.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @GetMapping("")
    public ModelAndView getAllPlayers(Model model, @RequestParam(defaultValue = "") String name, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        model.addAttribute("name", name);
        return new ModelAndView("player/list", "players", playerService.findByName(name, page));
    }

    @GetMapping("/create")
    public String formCreatePlayer(Model model) {
        model.addAttribute("player", new Player());
        return "player/add";
    }

    @PostMapping("/create")
    public String addPlayer(@Validated @ModelAttribute("player") Player player,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "player/add";
        }
        playerService.save(player);
        redirectAttributes.addFlashAttribute("message", "Player added successfully!");
        return "redirect:/player";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Player player = playerService.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        model.addAttribute("player", player);
        return "player/edit";
    }

    @PostMapping("/{id}/edit")
    public String updatePlayer(@PathVariable int id, @Validated @ModelAttribute("player") Player player,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "player/edit";
        }
        Player existingPlayer = playerService.findById(id);
        if (existingPlayer == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        playerService.update(id, player);
        redirectAttributes.addFlashAttribute("message", "Player updated successfully!");
        return "redirect:/player";
    }

    @GetMapping("/{id}/delete")
    public String deletePlayer(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Player player = playerService.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Player not found with id: " + id);
        }
        playerService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Player deleted successfully!");
        return "redirect:/player";
    }
}

