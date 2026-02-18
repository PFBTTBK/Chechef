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
import lombok.ToString;

@Data
@Entity
@Table(name = "omision")
public class Omision {

    private static final Long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_omision")
    private Long idOmision;

    @JoinColumn(name = "id_eleccion")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("eleccion - omision")
    private Eleccion eleccion;
    @JoinColumn(name = "id_ingrediente")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("ingrediente - omision")
    private Ingrediente ingrediente;
}
