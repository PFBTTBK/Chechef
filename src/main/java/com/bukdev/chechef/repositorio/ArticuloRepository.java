package com.bukdev.chechef.repositorio;

import com.bukdev.chechef.dominio.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
    
}
