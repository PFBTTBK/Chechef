package com.bukdev.chechef.servicio;

import com.bukdev.chechef.dominio.DetallesUsuario;
import com.bukdev.chechef.dominio.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.encontrar(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado por UserDetailsServiceImpl");
        }
        return new DetallesUsuario(usuario);
    }    
    
}
