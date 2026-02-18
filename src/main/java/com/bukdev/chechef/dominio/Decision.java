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
@Table(name = "decision")
public class Decision {

    private static final Long serialVersionUID = 1l;

    @Id
    @Column(name = "id_decision")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDecision;
    @JoinColumn(name = "id_eleccion")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("eleccion - decision")
    private Eleccion eleccion;
    @JoinColumn(name = "id_tipo")
    @ManyToOne
    @ToString.Exclude
    @JsonBackReference("tipo - decision")
    private Tipo tipo;
    
    public Decision(Tipo tipo){
        this.tipo = tipo;
    }

}
