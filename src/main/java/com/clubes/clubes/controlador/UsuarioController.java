package com.clubes.clubes.controlador;

import com.clubes.clubes.repository.PruebaTyTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.clubes.clubes.service.UsuarioDetailsService;
import com.clubes.clubes.repository.UsuarioRepository;
import com.clubes.clubes.entidades.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private PruebaTyTRepository pruebaTyTRepository;
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	@GetMapping("/login")
	public String mostrarLogin() {
		return "login";
	}

	@GetMapping("/registro")
	public String mostrarRegistro() {
		return "registro_usuario";
	}

	    @PostMapping("/registro")
	    public String procesarRegistro(@ModelAttribute Usuario usuario, @RequestParam("rol") String rol, RedirectAttributes redirectAttributes) {
	        try {
	            usuario.setRol(com.clubes.clubes.entidades.Rol.valueOf(rol.toUpperCase()));
	            usuarioDetailsService.registrarUsuario(usuario);
	            redirectAttributes.addFlashAttribute("exito", "Usuario registrado correctamente.");
	            return "redirect:/usuarios/registro"; // Redirige al mismo formulario para registrar otro
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("error", "Error al registrar el usuario: " + e.getMessage());
	            return "redirect:/usuarios/registro";
	        }
	    }
	@GetMapping("/perfil")
	public String mostrarPerfil(Model model, org.springframework.security.core.Authentication authentication) {
		if (authentication != null && authentication.isAuthenticated()) {
			String numeroDocumento = authentication.getName();
			Usuario usuario = usuarioRepository.findByNumeroDocumento(numeroDocumento);
			model.addAttribute("usuario", usuario);
			if (usuario != null) {
				java.util.List<com.clubes.clubes.entidades.PruebaTyT> pruebas = pruebaTyTRepository.findByUsuarioId(usuario.getId());
				model.addAttribute("pruebas", pruebas);
			}
		}
		return "perfil_usuario";
	}
}
