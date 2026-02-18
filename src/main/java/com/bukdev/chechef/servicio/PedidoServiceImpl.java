package com.bukdev.chechef.servicio;

import com.bukdev.chechef.dominio.Pedido;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.bukdev.chechef.repositorio.PedidoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private final PedidoRepository pedidoDao;

    @Override
    @Transactional
    public Long guardar(Pedido pedido) {
        return pedidoDao.save(pedido).getIdPedido();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Pedido encontrar(Long idPedido){
        Pedido pedido = pedidoDao.findById(idPedido).orElse(null);
        entityManager.refresh(pedido);
        return pedido;
    }
    
    @Override
    public List<Pedido> listar(LocalDateTime tiempo){
        return pedidoDao.findByTiempoGreaterThan(tiempo);
    }
    
}
