package com.clubes.clubes.controlador;

import com.clubes.clubes.entidades.Competicion;
import com.clubes.clubes.repository.CompeticionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/competiciones")
public class CompeticionControlador {

    private final CompeticionRepository competicionRepository;

    public CompeticionControlador(CompeticionRepository competicionRepository) {
        this.competicionRepository = competicionRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionRepository.findAll());
        return "competiciones";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        return "competicion_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Competicion competicion) {
        competicionRepository.save(competicion);
        return "redirect:/competiciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("competicion", competicionRepository.findById(id).orElseThrow());
        return "competicion_form";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        competicionRepository.deleteById(id);
        return "redirect:/competiciones";
    }
}