package com.example.dictionary.controller;

import com.example.dictionary.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DictionaryController {
    @Autowired
    private IDictionaryService dictionaryService;

    @GetMapping("")
    public String index() {
        return "index";
    }
    @GetMapping("/search")
    public String search(@RequestParam("word") String word, Model model) {
        if(word != null) {
            String meaning = dictionaryService.findMeaning(word);
            model.addAttribute("word", word);
            model.addAttribute("meaning", meaning);
        }
        return "result";
    }
}
