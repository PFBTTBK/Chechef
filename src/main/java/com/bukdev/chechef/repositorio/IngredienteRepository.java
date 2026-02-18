package com.bukdev.chechef.repositorio;

import com.bukdev.chechef.dominio.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
    
}
