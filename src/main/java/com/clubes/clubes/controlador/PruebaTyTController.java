package com.clubes.clubes.controlador;

import com.clubes.clubes.entidades.Rol;
import com.clubes.clubes.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.clubes.clubes.repository.PruebaTyTRepository;
import com.clubes.clubes.entidades.PruebaTyT;
import com.clubes.clubes.repository.UsuarioRepository;
import com.clubes.clubes.entidades.Usuario;

import java.util.Map;

@Controller
@RequestMapping("/pruebas")
public class PruebaTyTController {
    @Autowired
    private PruebaTyTRepository pruebaTyTRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @GetMapping("/registrar")
    public String mostrarRegistroPrueba() {
        return "registro_prueba";
    }

    @PostMapping("/registrar")
    public String procesarRegistroPrueba(@RequestParam Map<String, String> allParams, RedirectAttributes redirectAttributes) {
        try {
            String numeroDocumento = allParams.get("numeroDocumento");
            Usuario usuario = usuarioRepository.findByNumeroDocumento(numeroDocumento);

            if (usuario == null) {
                // Crear nuevo usuario
                String contrasena = allParams.get("contrasena");
                if (contrasena == null || contrasena.trim().isEmpty()) {
                    redirectAttributes.addFlashAttribute("error", "La contraseña es obligatoria para nuevos estudiantes.");
                    return "redirect:/pruebas/registrar";
                }

                usuario = new Usuario();
                usuario.setNumeroDocumento(numeroDocumento);
                usuario.setTipoDocumento(allParams.get("tipoDocumento"));
                usuario.setPrimerNombre(allParams.get("primerNombre"));
                usuario.setSegundoNombre(allParams.get("segundoNombre"));
                usuario.setPrimerApellido(allParams.get("primerApellido"));
                usuario.setSegundoApellido(allParams.get("segundoApellido"));
                usuario.setCorreo(allParams.get("correo"));
                usuario.setTelefono(allParams.get("telefono"));
                usuario.setContrasena(contrasena); // Se pasa la contraseña en texto plano
                usuario.setRol(Rol.ESTUDIANTE);

                usuario = usuarioDetailsService.registrarUsuario(usuario); // El servicio encripta y guarda
            }

            // Crear y guardar la prueba
            PruebaTyT prueba = new PruebaTyT();
            prueba.setUsuarioId(usuario.getId());
            prueba.setNumeroRegistro(allParams.get("numeroRegistro"));
            prueba.setPuntajeGlobal(Integer.parseInt(allParams.get("puntajeGlobal")));
            prueba.setComunicacionEscrita(Integer.parseInt(allParams.get("comunicacionEscrita")));
            prueba.setComunicacionEscritaNivel(allParams.get("comunicacionEscritaNivel"));
            prueba.setRazonamientoCuantitativo(Integer.parseInt(allParams.get("razonamientoCuantitativo")));
            prueba.setRazonamientoCuantitativoNivel(allParams.get("razonamientoCuantitativoNivel"));
            prueba.setLecturaCritica(Integer.parseInt(allParams.get("lecturaCritica")));
            prueba.setLecturaCriticaNivel(allParams.get("lecturaCriticaNivel"));
            prueba.setCompetenciasCiudadanas(Integer.parseInt(allParams.get("competenciasCiudadanas")));
            prueba.setCompetenciasCiudadanasNivel(allParams.get("competenciasCiudadanasNivel"));
            prueba.setIngles(Integer.parseInt(allParams.get("ingles")));
            prueba.setFormulacionProyectosIngenieria(Integer.parseInt(allParams.get("formulacionProyectosIngenieria")));
            prueba.setFormulacionProyectosIngenieriaNivel(allParams.get("formulacionProyectosIngenieriaNivel"));
            prueba.setPensamientoCientificoMatematicasEstadistica(Integer.parseInt(allParams.get("pensamientoCientificoMatematicasEstadistica")));
            prueba.setPensamientoCientificoMatematicasEstadisticaNivel(allParams.get("pensamientoCientificoMatematicasEstadisticaNivel"));
            prueba.setDisenoSoftware(Integer.parseInt(allParams.get("disenoSoftware")));

            pruebaTyTRepository.save(prueba);
            redirectAttributes.addFlashAttribute("exito", "Puntaje registrado y usuario actualizado/creado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al registrar el puntaje. Verifica los datos. " + e.getMessage());
        }
        return "redirect:/pruebas/registrar";
    }

    @GetMapping("/listar")
    public String mostrarListaPruebas(Model model) {
        java.util.List<PruebaTyT> pruebas = pruebaTyTRepository.findAll();
        java.util.List<java.util.Map<String, Object>> pruebasConUsuarios = new java.util.ArrayList<>();
        for (PruebaTyT prueba : pruebas) {
            Usuario usuario = usuarioRepository.findById(prueba.getUsuarioId()).orElse(null);
            java.util.Map<String, Object> pruebaConUsuario = new java.util.HashMap<>();
            pruebaConUsuario.put("prueba", prueba);
            pruebaConUsuario.put("usuario", usuario);
            pruebasConUsuarios.add(pruebaConUsuario);
        }
        model.addAttribute("pruebasConUsuarios", pruebasConUsuarios);
        return "lista_pruebas";
    }
    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarEditarPrueba(@org.springframework.web.bind.annotation.PathVariable String id, Model model) {
        PruebaTyT prueba = pruebaTyTRepository.findById(id).orElse(null);
        if (prueba == null) {
            model.addAttribute("error", "Registro no encontrado");
            return "redirect:/pruebas/listar";
        }
        Usuario usuario = usuarioRepository.findById(prueba.getUsuarioId()).orElse(null);
        model.addAttribute("prueba", prueba);
        model.addAttribute("usuario", usuario);
        return "editar_prueba";
    }

    @PostMapping("/editar/{id}")
    public String procesarEditarPrueba(@org.springframework.web.bind.annotation.PathVariable String id,
                                       @RequestParam Map<String, String> allParams,
                                       RedirectAttributes redirectAttributes) {
        PruebaTyT prueba = pruebaTyTRepository.findById(id).orElse(null);
        if (prueba == null) {
            redirectAttributes.addFlashAttribute("error", "Registro de prueba no encontrado");
            return "redirect:/pruebas/listar";
        }

        Usuario usuario = usuarioRepository.findById(prueba.getUsuarioId()).orElse(null);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("error", "Usuario asociado no encontrado");
            return "redirect:/pruebas/listar";
        }

        try {
            // Actualizar datos del usuario
            usuario.setTipoDocumento(allParams.get("tipoDocumento"));
            usuario.setNumeroDocumento(allParams.get("numeroDocumento"));
            usuario.setPrimerApellido(allParams.get("primerApellido"));
            usuario.setSegundoApellido(allParams.get("segundoApellido"));
            usuario.setPrimerNombre(allParams.get("primerNombre"));
            usuario.setSegundoNombre(allParams.get("segundoNombre"));
            usuario.setCorreo(allParams.get("correo"));
            usuario.setTelefono(allParams.get("telefono"));
            usuarioRepository.save(usuario);

            // Actualizar datos de la prueba
            prueba.setNumeroRegistro(allParams.get("numeroRegistro"));
            prueba.setPuntajeGlobal(Integer.parseInt(allParams.get("puntajeGlobal")));
            prueba.setComunicacionEscrita(Integer.parseInt(allParams.get("comunicacionEscrita")));
            prueba.setComunicacionEscritaNivel(allParams.get("comunicacionEscritaNivel"));
            prueba.setRazonamientoCuantitativo(Integer.parseInt(allParams.get("razonamientoCuantitativo")));
            prueba.setRazonamientoCuantitativoNivel(allParams.get("razonamientoCuantitativoNivel"));
            prueba.setLecturaCritica(Integer.parseInt(allParams.get("lecturaCritica")));
            prueba.setLecturaCriticaNivel(allParams.get("lecturaCriticaNivel"));
            prueba.setCompetenciasCiudadanas(Integer.parseInt(allParams.get("competenciasCiudadanas")));
            prueba.setCompetenciasCiudadanasNivel(allParams.get("competenciasCiudadanasNivel"));
            prueba.setIngles(Integer.parseInt(allParams.get("ingles")));
            prueba.setFormulacionProyectosIngenieria(Integer.parseInt(allParams.get("formulacionProyectosIngenieria")));
            prueba.setFormulacionProyectosIngenieriaNivel(allParams.get("formulacionProyectosIngenieriaNivel"));
            prueba.setPensamientoCientificoMatematicasEstadistica(Integer.parseInt(allParams.get("pensamientoCientificoMatematicasEstadistica")));
            prueba.setPensamientoCientificoMatematicasEstadisticaNivel(allParams.get("pensamientoCientificoMatematicasEstadisticaNivel"));
            prueba.setDisenoSoftware(Integer.parseInt(allParams.get("disenoSoftware")));
            pruebaTyTRepository.save(prueba);

            redirectAttributes.addFlashAttribute("exito", "Registro actualizado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el registro: " + e.getMessage());
        }

        return "redirect:/pruebas/listar";
    }

    // Eliminar registro
    @GetMapping("/eliminar/{id}")
    public String eliminarPrueba(@org.springframework.web.bind.annotation.PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            pruebaTyTRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("exito", "Registro eliminado correctamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el registro.");
        }
        return "redirect:/pruebas/listar";
    }
        // Vista de detalles de nota
    @GetMapping("/detalle/{id}")
    public String mostrarDetallePrueba(@org.springframework.web.bind.annotation.PathVariable String id, Model model) {
        PruebaTyT prueba = pruebaTyTRepository.findById(id).orElse(null);
        if (prueba == null) {
            model.addAttribute("error", "Registro no encontrado");
            return "redirect:/pruebas/listar";
        }
        Usuario usuario = usuarioRepository.findById(prueba.getUsuarioId()).orElse(null);
        model.addAttribute("prueba", prueba);
        model.addAttribute("usuario", usuario);
        return "detalle_prueba";
    }
}
