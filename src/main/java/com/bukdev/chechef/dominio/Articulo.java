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
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "articulo")
public class Articulo {

    private static final Long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_articulo")
    private Long idArticulo;

    private String nombre;
    private String foto;
    private String responsable;
    
    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    @JsonManagedReference("articulo - eleccion")
    private List<Eleccion> elecciones;
    @OneToMany(mappedBy = "articulo", cascade = CascadeType.ALL)
    @JsonManagedReference("articulo - ingrediente")
    private List<Ingrediente> ingredientes;
    
    public Articulo(Long idArticulo, String nombre){
        this.idArticulo = idArticulo;
        this.nombre = nombre;
    }
}
