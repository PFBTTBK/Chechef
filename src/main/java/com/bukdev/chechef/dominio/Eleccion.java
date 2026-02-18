package com.bukdev.chechef.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name = "eleccion")
public class Eleccion {

    private static final Long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_eleccion")
    private Long idEleccion;

    
    @JoinColumn(name = "id_articulo")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("articulo - eleccion")
    private Articulo articulo;
    @JoinColumn(name = "id_pedido")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("pedido - eleccion")
    private Pedido pedido;
    
    @OneToMany(mappedBy = "eleccion", cascade = CascadeType.ALL)
    @JsonManagedReference("eleccion - decision")
    private List<Decision> decisiones;
    
    @OneToMany(mappedBy = "eleccion", cascade = CascadeType.ALL)
    @JsonManagedReference("eleccion - omision")
    private List<Omision> omisiones;
    
    public Eleccion(Articulo articulo){
        this.articulo = articulo;
    }
}
