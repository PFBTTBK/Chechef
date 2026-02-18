package com.bukdev.chechef.utiles;

import com.bukdev.chechef.dominio.Pedido;
import com.bukdev.chechef.dto.PedidoDTO;
import com.bukdev.chechef.mapper.PedidoMapper;
import com.bukdev.chechef.servicio.PedidoService;
import com.bukdev.chechef.servicio.ServicioSSE;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@RequiredArgsConstructor
public class ControladorRESTUtiles {
    
    private final PedidoService pedidoService;
    
    private final PedidoUtiles pedidoUtiles;
    
    private final ServicioSSE servicioSSE;
    
    private final ObjectMapper objectMapper;
    
    private final PedidoMapper pedidoMapper;
    
    public void recibirPedido(Pedido pedido, String idCliente){
        Long idPedido = pedidoService.guardar(pedidoUtiles.asignarRelaciones(pedido));
        pedido = pedidoService.encontrar(idPedido);
        PedidoDTO pedidoDTO = pedidoMapper.toDTO(pedido);
        try {
            servicioSSE.enviarPedido(idCliente, objectMapper.writeValueAsString(pedidoDTO));
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ControladorRESTUtiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public SseEmitter conectarSSE(String username){
        return servicioSSE.crearEmitter(username);
    }
    
}
