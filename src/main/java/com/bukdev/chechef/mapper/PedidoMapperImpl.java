package com.bukdev.chechef.mapper;

import com.bukdev.chechef.dominio.Pedido;
import com.bukdev.chechef.dto.EleccionDTO;
import com.bukdev.chechef.dto.PedidoDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoMapperImpl implements PedidoMapper{
    
    private final EleccionMapper eleccionMapper;

    @Override
    public PedidoDTO toDTO(Pedido pedido) {
        List<EleccionDTO> eleccionesDTO = eleccionMapper.toDTOList(pedido.getElecciones());
        
        return pedido.getNombre() != null
                ? new PedidoDTO(pedido.getNombre(), pedido.getTiempo(), eleccionesDTO)
                : new PedidoDTO(pedido.getMesa(), pedido.getTiempo(), eleccionesDTO);
    }

    @Override
    public List<PedidoDTO> toDTOList(List<Pedido> pedidos) {
        return pedidos.stream().map(this::toDTO).collect(Collectors.toList());
    }
    
}
