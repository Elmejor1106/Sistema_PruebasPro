package com.clubes.clubes.controlador;

import com.clubes.clubes.entidades.Entrenador;
import com.clubes.clubes.repository.EntrenadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorControlador {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorControlador(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        return "entrenadores";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        return "entrenador_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Entrenador entrenador) {
        entrenadorRepository.save(entrenador);
        return "redirect:/entrenadores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("entrenador", entrenadorRepository.findById(id).orElseThrow());
        return "entrenador_form";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        entrenadorRepository.deleteById(id);
        return "redirect:/entrenadores";
    }
}