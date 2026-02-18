package com.bukdev.chechef.dominio;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tipo")
public class Tipo{
    
    private static final Long serialVersionUID = 1l;
    
    @Id
    @Column(name = "id_tipo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;
    private String tipo;
    private boolean seleccionado;
    @JoinColumn(name = "id_ingrediente")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("ingrediente - tipo")
    private Ingrediente ingrediente;
    private String foto;
    
    public Tipo (Long idTipo, String tipo){
        this.idTipo = idTipo;
        this.tipo = tipo;
        
    }
    
}