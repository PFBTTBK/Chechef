package com.bukdev.chechef.utiles;

import com.bukdev.chechef.dominio.Pedido;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.stereotype.Service;

@Service
public class PedidoUtiles {
    public Pedido asignarRelaciones(Pedido pedido){
        pedido.getElecciones().forEach(eleccion -> {
            eleccion.setPedido(pedido);
            asignarPadre(eleccion.getDecisiones(), eleccion, d -> d.setEleccion(eleccion));
            asignarPadre(eleccion.getOmisiones(), eleccion, o -> o.setEleccion(eleccion));
        });
        return pedido;
    }
    
    private <T, P> void asignarPadre(List<T> lista, P padre, Consumer<T> asignador){
        lista.forEach(asignador);
    }
}
