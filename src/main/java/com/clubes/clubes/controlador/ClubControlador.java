package com.clubes.clubes.controlador;

import com.clubes.clubes.entidades.Club;
import com.clubes.clubes.repository.ClubRepository;
import com.clubes.clubes.repository.EntrenadorRepository;
import com.clubes.clubes.repository.AsociacionRepository;
import com.clubes.clubes.repository.CompeticionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clubes")
public class ClubControlador {

    private final ClubRepository clubRepository;
    private final EntrenadorRepository entrenadorRepository;
    private final AsociacionRepository asociacionRepository;
    private final CompeticionRepository competicionRepository;

    public ClubControlador(
        ClubRepository clubRepository,
        EntrenadorRepository entrenadorRepository,
        AsociacionRepository asociacionRepository,
        CompeticionRepository competicionRepository
    ) {
        this.clubRepository = clubRepository;
        this.entrenadorRepository = entrenadorRepository;
        this.asociacionRepository = asociacionRepository;
        this.competicionRepository = competicionRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepository.findAll());
        return "clubes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("competicionesLista", competicionRepository.findAll());
        return "club_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Club club) {
        clubRepository.save(club);
        return "redirect:/clubes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("club", clubRepository.findById(id).orElseThrow());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("competicionesLista", competicionRepository.findAll());
        return "club_form";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        clubRepository.deleteById(id);
        return "redirect:/clubes";
    }
}