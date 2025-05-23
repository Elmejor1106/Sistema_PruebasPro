package com.clubes.clubes.controlador;
import com.clubes.clubes.entidades.Asociacion;
import com.clubes.clubes.repository.AsociacionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/asociaciones")
public class AsociacionControlador {

    private final AsociacionRepository asociacionRepository;

    public AsociacionControlador(AsociacionRepository asociacionRepository) {
        this.asociacionRepository = asociacionRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        return "asociaciones";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        return "asociacion_form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Asociacion asociacion) {
        asociacionRepository.save(asociacion);
        return "redirect:/asociaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("asociacion", asociacionRepository.findById(id).orElseThrow());
        return "asociacion_form";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        asociacionRepository.deleteById(id);
        return "redirect:/asociaciones";
    }
}