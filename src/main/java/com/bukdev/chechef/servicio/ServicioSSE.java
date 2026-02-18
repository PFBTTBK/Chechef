package com.bukdev.chechef.servicio;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class ServicioSSE {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
    
    public SseEmitter crearEmitter(String clienteId){
        SseEmitter emitter = new SseEmitter(0L);
        emitters.put(clienteId, emitter);
        
        emitter.onCompletion(() -> emitters.remove(clienteId));
        emitter.onTimeout(() -> emitters.remove(clienteId));
        emitter.onError(e -> emitters.remove(clienteId));
        
        Logger.getLogger(ServicioSSE.class.getName()).log(Level.INFO, "crearEmitter:16: " + emitter);
        return emitter;
    }
    
    public void enviarPedido(String idCliente, String Json){
        SseEmitter emitter = emitters.get(idCliente);
        Logger.getLogger(ServicioSSE.class.getName()).log(Level.INFO, emitters.toString() + ":29");
        if (emitter != null) {
            try {
                Logger.getLogger(ServicioSSE.class.getName()).log(Level.INFO, Json);
                emitter.send(SseEmitter.event().name("Pedido").data(Json));
            } catch (Exception e) {
                emitters.remove(idCliente);
                Logger.getLogger(ServicioSSE.class.getName()).log(Level.SEVERE, "Exception en ServicioSSE.enviarPedido:33", e);
            }
        } else {
            Logger.getLogger(ServicioSSE.class.getName()).log(Level.SEVERE, "emitter = null:37");
        }
    }
}