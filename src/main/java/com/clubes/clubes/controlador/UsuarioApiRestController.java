package com.clubes.clubes.controlador;

import com.clubes.clubes.entidades.Usuario;
import com.clubes.clubes.repository.UsuarioRepository;
import com.clubes.clubes.service.UsuarioDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioApiRestController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        Usuario user = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (user != null && passwordEncoder.matches(usuario.getContrasena(), user.getContrasena())) {
            return user;
        }
        return null;
    }

    @PostMapping("/registro")
    public Usuario registrar(@ModelAttribute Usuario usuario) {
        return usuarioDetailsService.registrarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable String id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
