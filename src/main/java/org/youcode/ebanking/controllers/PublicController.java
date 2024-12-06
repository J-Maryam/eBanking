package org.youcode.ebanking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @GetMapping("/api/notices")
    public String notice() {
        return "List des annonces:  Système mis à jour le 4 décembre 2024. Aucune interruption prévue.";
    }

    @GetMapping("/api/contact")
    public String contact() {
        return "support client contact List";
    }
}
