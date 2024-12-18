package com.example.mailbox_config.controller;

import com.example.mailbox_config.model.MailConfig;
import com.example.mailbox_config.service.IMailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MailConfigController {
    @Autowired
    private IMailConfigService service;

    @GetMapping("/")
    public String showSettings(Model model) {
        if (!model.containsAttribute("mailConfig")) {
            MailConfig currentConfig = service.getCurrentConfig();
            model.addAttribute("mailConfig", currentConfig);
        }
        return "settings";
    }

    @PostMapping("/update-settings")
    public String updateSettings(@ModelAttribute("mailConfig") MailConfig mailConfig, Model model) {
        service.saveConfig(mailConfig);
        model.addAttribute("mailConfig", mailConfig);
        return "result";
    }
}
