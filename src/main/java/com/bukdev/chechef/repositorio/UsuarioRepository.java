package com.bukdev.chechef.repositorio;

import com.bukdev.chechef.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Usuario findByUsername(String username);
    
}
