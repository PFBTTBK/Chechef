package com.bukdev.chechef.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PedidoDTO {
    
    private String nombre;
    private int mesa;
    private LocalDateTime tiempo;
    
    private List<EleccionDTO> elecciones;
    
    public PedidoDTO(String nombre, LocalDateTime tiempo, List<EleccionDTO> elecciones){
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.elecciones = elecciones;
    }
    
    public PedidoDTO(int mesa, LocalDateTime tiempo, List<EleccionDTO> elecciones){
        this.mesa = mesa;
        this.tiempo = tiempo;
        this.elecciones = elecciones;
    }
    
}
