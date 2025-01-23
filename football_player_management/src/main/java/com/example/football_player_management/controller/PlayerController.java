package com.example.football_player_management.controller;

import com.example.football_player_management.exception.PlayerNotFoundException;
import com.example.football_player_management.model.Player;
import com.example.football_player_management.service.IPlayerService;
import com.example.football_player_management.service.ITeamService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ITeamService teamService;

    @GetMapping("")
    public ModelAndView getAllPlayers(Model model, @RequestParam(defaultValue = "") String name, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        model.addAttribute("name", name);
        return new ModelAndView("player/list", "players", playerService.findByName(name, page));
    }

    @GetMapping("/create")
    public String formCreatePlayer(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamService.getAllTeams());
        return "player/add";
    }

    @PostMapping("/create")
    public String addPlayer(@Validated @ModelAttribute("player") Player player,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {
        if (!player.isAgeValid()) {
            bindingResult.rejectValue("dob", null, player.getAgeErrorMessage());
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("teams", teamService.getAllTeams());
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
        model.addAttribute("team", teamService.getAllTeams());
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

    @PostMapping("/{id}/register")
    public String registerPlayer(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            playerService.registerPlayer(id);
            redirectAttributes.addFlashAttribute("message", "Cầu thủ đã được đăng ký thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/error/404";
        }
        return "redirect:/player";
    }

    @PostMapping("/{id}/unregister")
    public String unregisterPlayer(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            playerService.unregisterPlayer(id);
            redirectAttributes.addFlashAttribute("message", "Cầu thủ đã được chuyển về trạng thái Dự bị!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/error/404";
        }
        return "redirect:/player";
    }

    @GetMapping("/{id}")
    public String getPlayerDetails(@PathVariable int id, Model model) {
        Player player = playerService.findById(id);
        if (player == null) {
            throw new PlayerNotFoundException("Không tìm thấy cầu thủ.");
        }
        model.addAttribute("player", player);
        return "player/detail";
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model, HttpSession session) {
        List<Player> favoritePlayers = (List<Player>) session.getAttribute("favorites");
        if (favoritePlayers == null) {
            favoritePlayers = new ArrayList<>();
        }else {
            List<Integer> favoriteIds = favoritePlayers.stream().map(Player::getId).collect(Collectors.toList());
            favoritePlayers = playerService.findAllById(favoriteIds);
        }

        model.addAttribute("favorites", favoritePlayers);
        return "player/favorites";
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<Void> addToFavorites(@PathVariable int id, HttpSession session) {
        Player player = playerService.findById(id);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        List<Player> favoritePlayers = (List<Player>) session.getAttribute("favorites");
        if (favoritePlayers == null) {
            favoritePlayers = new ArrayList<>();
        }

        if (!favoritePlayers.contains(player)) {
            favoritePlayers.add(player);
            session.setAttribute("favorites", favoritePlayers);
        }
        System.out.println("Danh sách yêu thích hiện tại: " + favoritePlayers);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/favorite/save")
    public ResponseEntity<Void> saveFavoritesToCookie(HttpSession session, HttpServletResponse response) {
        List<Player> favoritePlayers = (List<Player>) session.getAttribute("favorites");
        if (favoritePlayers != null) {
            String favoriteIds = favoritePlayers.stream()
                    .map(player -> String.valueOf(player.getId()))
                    .collect(Collectors.joining(","));
            Cookie cookie = new Cookie("favorites", favoriteIds);
            cookie.setMaxAge(7 * 24 * 60 * 60); // Lưu trong 7 ngày
            response.addCookie(cookie);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorite/load")
    public String loadFavoritesFromCookie(@CookieValue(value = "favorites", defaultValue = "") String favorites, HttpSession session) {
        if (!favorites.isEmpty()) {
            List<Integer> favoriteIds = Arrays.stream(favorites.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            List<Player> favoritePlayers = playerService.findAllById(favoriteIds);
            session.setAttribute("favorites", favoritePlayers);
        }
        return "redirect:/player/favorites";
    }
}

