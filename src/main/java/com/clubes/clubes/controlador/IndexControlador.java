package com.clubes.clubes.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/usuarios/login";
    }

    @GetMapping("/inicio")
    public String index() {
        return "index";
    }
}