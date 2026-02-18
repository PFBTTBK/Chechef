package com.bukdev.chechef.mapper;

import com.bukdev.chechef.dominio.Pedido;
import com.bukdev.chechef.dto.PedidoDTO;
import java.util.List;

public interface PedidoMapper {
    
    PedidoDTO toDTO(Pedido pedido);
    
    List<PedidoDTO> toDTOList(List<Pedido> pedidos);
    
}
