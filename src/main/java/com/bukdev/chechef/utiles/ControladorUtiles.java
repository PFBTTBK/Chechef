package com.bukdev.chechef.utiles;

import com.bukdev.chechef.dominio.Pedido;
import com.bukdev.chechef.dto.PedidoDTO;
import com.bukdev.chechef.mapper.PedidoMapper;
import com.bukdev.chechef.servicio.ArticuloServicio;
import com.bukdev.chechef.servicio.PedidoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
@RequiredArgsConstructor
public class ControladorUtiles {
    
    private static final Logger logger = LoggerFactory.getLogger(ControladorUtiles.class);
    
    private final ArticuloServicio articuloServicio;
    
    private final PedidoService pedidoService;
    
    private final PedidoMapper pedidoMapper;
    
    public void modelarRegistrarHTML(Model model){
        model.addAttribute("articulos", articuloServicio.listar());
    }
    
    public void modelarRegistroHTML(Model model){
        List<Pedido> pedidos = pedidoService.listar(LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0)));
        List<PedidoDTO> pedidosDTO = pedidoMapper.toDTOList(pedidos);
        model.addAttribute("pedidos", pedidosDTO);
    }
}
