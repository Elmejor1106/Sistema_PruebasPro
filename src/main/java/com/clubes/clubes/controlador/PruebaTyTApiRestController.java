package com.clubes.clubes.controlador;

import com.clubes.clubes.entidades.PruebaTyT;
import com.clubes.clubes.repository.PruebaTyTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pruebas")
public class PruebaTyTApiRestController {
    @Autowired
    private PruebaTyTRepository pruebaTyTRepository;

    @PostMapping("/registrar")
    public PruebaTyT registrarPrueba(@ModelAttribute PruebaTyT prueba) {
        return pruebaTyTRepository.save(prueba);
    }

    @GetMapping
    public List<PruebaTyT> listarPruebas() {
        return pruebaTyTRepository.findAll();
    }

    @GetMapping("/{id}")
    public PruebaTyT obtenerPrueba(@PathVariable String id) {
        return pruebaTyTRepository.findById(id).orElse(null);
    }

    @GetMapping("/incentivo/{id}")
    public String calcularIncentivo(@PathVariable String id) {
        PruebaTyT prueba = pruebaTyTRepository.findById(id).orElse(null);
        if (prueba == null) return "No encontrado";
        int puntaje = prueba.getPuntajeGlobal();
        if (puntaje < 80) {
            return "Alerta roja: No puede graduarse. Los estudiantes que obtengan un resultado inferior a 80 puntos no podrán graduarse, razón por la cual, la preparación y presentación de las pruebas de estado se debe realizar con la misma seriedad, interés e importancia que cualquiera de las asignaturas que se toman semestre a semestre.";
        } else if (puntaje >= 180 && puntaje <= 210) {
            return "Exoneración del informe final de trabajo de grado o Seminario de grado IV con nota 4.5. No hay beca adicional.";
        } else if (puntaje >= 211 && puntaje <= 240) {
            return "Exoneración del informe final de trabajo de grado o Seminario de grado IV con nota 4.7. Beca del 50% en derechos de grado.";
        } else if (puntaje > 241) {
            return "Exoneración del informe final de trabajo de grado o Seminario de grado IV con nota 5.0. Beca del 100% en derechos de grado.";
        } else {
            return "Sin incentivo especial.";
        }
    }
}
