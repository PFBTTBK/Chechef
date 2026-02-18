package com.bukdev.chechef.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class EleccionDTO {
    
    private ArticuloDTO articulo;
    private String decisiones;
    private String omisiones;
    private int cantidad;
    
}
