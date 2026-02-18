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
@Table(name = "ingrediente")
public class Ingrediente {

    private static final Long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Long idIngrediente;

    private String nombre;
    @JoinColumn(name = "id_articulo")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("articulo - ingrediente")
    private Articulo articulo;
    private String foto;
    
    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    @JsonManagedReference("ingrediente - tipo")
    private List<Tipo> tipos;
    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    @JsonManagedReference("ingrediente - omision")
    private List<Omision> omisiones;
    
    public Ingrediente(Long idIngrediente, String nombre){
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
    }
    
}
