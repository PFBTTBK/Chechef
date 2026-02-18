package com.bukdev.chechef.servicio;

import com.bukdev.chechef.dominio.Usuario;
import com.bukdev.chechef.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    
    private final UsuarioRepository UsuarioRepository;

    @Override
    public Usuario encontrar(String username) {
        return UsuarioRepository.findByUsername(username);
    }
    
}
