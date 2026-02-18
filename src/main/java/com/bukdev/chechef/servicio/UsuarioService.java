package com.bukdev.chechef.servicio;

import com.bukdev.chechef.dominio.Usuario;

public interface UsuarioService {
    
    Usuario encontrar(String username);
    
}
