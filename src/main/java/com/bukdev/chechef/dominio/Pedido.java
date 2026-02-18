package com.bukdev.chechef.dominio;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    private static final Long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long idPedido;

    private String nombre;
    private int mesa;
    private LocalDateTime tiempo;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonManagedReference("pedido - eleccion")
    private List<Eleccion> elecciones;
}
