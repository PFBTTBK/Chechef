package com.bukdev.chechef.repositorio;

import com.bukdev.chechef.dominio.Pedido;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByTiempoGreaterThan(LocalDateTime tiempo);
}
