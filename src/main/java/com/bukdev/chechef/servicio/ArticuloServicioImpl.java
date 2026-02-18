package com.bukdev.chechef.servicio;

import com.bukdev.chechef.dominio.Articulo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bukdev.chechef.repositorio.ArticuloRepository;

@RequiredArgsConstructor
@Service
public class ArticuloServicioImpl implements ArticuloServicio {
    
    private final ArticuloRepository articuloDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Articulo> listar(){
        return (List<Articulo>)articuloDao.findAll();
    }
}
