package com.bukdev.chechef.mapper;

import com.bukdev.chechef.dominio.Eleccion;
import com.bukdev.chechef.dto.EleccionDTO;
import java.util.List;
import java.util.function.Function;

public interface EleccionMapper {
    
    <T> String toString(List<T> lista, Function<T, String> mapper, String inicio);
    
    EleccionDTO toDTO(Eleccion eleccion);
    
    List<EleccionDTO> toDTOList(List<Eleccion> elecciones);

}