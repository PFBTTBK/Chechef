package com.bukdev.chechef.servicio;

import com.bukdev.chechef.dominio.Pedido;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoService {
    
    public Long guardar(Pedido pedido);
    
    public Pedido encontrar(Long idPedido);
    
    public List<Pedido> listar(LocalDateTime tiempo);
    
}
