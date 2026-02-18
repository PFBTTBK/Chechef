package com.bukdev.chechef.mapper;

import com.bukdev.chechef.dominio.Eleccion;
import com.bukdev.chechef.dto.ArticuloDTO;
import com.bukdev.chechef.dto.EleccionDTO;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class EleccionMapperImpl implements EleccionMapper{
    
    @Override
     public <T> String toString(List<T> lista, Function<T, String> mapper, String inicio){
        return !lista.isEmpty() ? (inicio + lista.stream().map(mapper).collect(Collectors.joining(", "))) : "";
    }
    
    @Override
    public EleccionDTO toDTO(Eleccion eleccion) {
        return new EleccionDTO(new ArticuloDTO(eleccion.getArticulo().getNombre(), eleccion.getArticulo().getResponsable())
                , toString(eleccion.getDecisiones(), d -> d.getTipo().getTipo(), "Con: ")
                , toString(eleccion.getOmisiones(), o -> o.getIngrediente().getNombre(), "Sin: ")
                , 1);
    }

    @Override
    public List<EleccionDTO> toDTOList(List<Eleccion> elecciones) {
        List<EleccionDTO> lista = elecciones.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        
        Map<EleccionDTO, Integer> mapa = lista.stream()
                .collect(Collectors.groupingBy(
                        Function.identity()
                        , Collectors.summingInt(EleccionDTO::getCantidad)
                ));
        
        return mapa.entrySet().stream().map(e -> {
            e.getKey().setCantidad(e.getValue());
            return e.getKey();
        }).sorted(Comparator.comparing(eleccionDTO -> {
            return eleccionDTO.getArticulo() + eleccionDTO.getDecisiones() + eleccionDTO.getOmisiones();
        })).collect(Collectors.toList());
    }
}
