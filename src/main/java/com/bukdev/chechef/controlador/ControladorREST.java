package com.bukdev.chechef.controlador;

import com.bukdev.chechef.dominio.Pedido;
import com.bukdev.chechef.utiles.ControladorRESTUtiles;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RequiredArgsConstructor
@RestController
public class ControladorREST {
    
    private final ControladorRESTUtiles controladorRESTUtiles;
    
    @PostMapping("/insertar")
    public ResponseEntity<?> insertarPedido(@RequestBody(required = true) Pedido pedido){
        controladorRESTUtiles.recibirPedido(pedido, "Cocina");
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @GetMapping("/conectarSSE")
    public SseEmitter conectarSSE(Principal principal){
        return controladorRESTUtiles.conectarSSE(principal.getName());
    }
    
}
