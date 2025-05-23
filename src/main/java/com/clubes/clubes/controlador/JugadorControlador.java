package com.clubes.clubes.controlador;

import com.clubes.clubes.entidades.Jugador;
import com.clubes.clubes.repository.JugadorRepository;
import com.clubes.clubes.repository.ClubRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugadores")
public class JugadorControlador {

    private final JugadorRepository jugadorRepository;
    private final ClubRepository clubRepository;

    public JugadorControlador(JugadorRepository jugadorRepository, ClubRepository clubRepository) {
        this.jugadorRepository = jugadorRepository;
        this.clubRepository = clubRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorRepository.findAll());
        return "jugadores";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("clubes", clubRepository.findAll());
        return "jugador_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Jugador jugador) {
        jugadorRepository.save(jugador);
        return "redirect:/jugadores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("jugador", jugadorRepository.findById(id).orElseThrow());
        model.addAttribute("clubes", clubRepository.findAll());
        return "jugador_form";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        jugadorRepository.deleteById(id);
        return "redirect:/jugadores";
    }
}