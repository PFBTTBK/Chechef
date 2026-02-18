package com.bukdev.chechef.repositorio;

import com.bukdev.chechef.dominio.Eleccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EleccionRepository extends JpaRepository<Eleccion, Long>{
    
}
