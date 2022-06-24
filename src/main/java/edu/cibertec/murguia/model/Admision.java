package edu.cibertec.murguia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="admision")
@NoArgsConstructor
@AllArgsConstructor
public class Admision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String estado;

    public Admision(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
