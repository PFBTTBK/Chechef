package com.bukdev.chechef.controlador;

import com.bukdev.chechef.utiles.ControladorUtiles;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class Controlador {
    
    private static final Logger logger = LoggerFactory.getLogger(Controlador.class);
    
    private final ControladorUtiles controladorUtiles;
    
    @GetMapping("/registrar")
    public String registrar (Model model){
        controladorUtiles.modelarRegistrarHTML(model);
        return "registrar";
    }
    
    @GetMapping("/registro")
    public String registro (Model model){
        controladorUtiles.modelarRegistroHTML(model);
        return "registro";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login.html";
    }
}
