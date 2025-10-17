package com.clubes.clubes.service;

import com.clubes.clubes.entidades.Usuario;
import com.clubes.clubes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("[DEBUG] Intentando autenticar usuario: " + username);
        Usuario usuario = usuarioRepository.findByNumeroDocumento(username);
        if (usuario == null) {
            System.out.println("[ERROR] Usuario no encontrado: " + username);
            throw new UsernameNotFoundException("Usuario no encontrado con el documento: " + username);
        }
        System.out.println("[DEBUG] Usuario encontrado: " + usuario.getNumeroDocumento() + ", rol: " + usuario.getRol());
        String role = usuario.getRol() == com.clubes.clubes.entidades.Rol.COORDINADOR ? "ROLE_COORDINADOR" : "ROLE_ESTUDIANTE";
        System.out.println("[DEBUG] Rol asignado a Spring Security: " + role);
        return new User(
                usuario.getNumeroDocumento(),
                usuario.getContrasena(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }

    public Usuario registrarUsuario(Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return usuarioRepository.save(usuario);
    }
}
