package edu.cibertec.murguia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="carrera")
@NoArgsConstructor
@AllArgsConstructor
public class Carrera {
    //id,exam
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String estado;

    //muchas carreras pertenecen a una sola area
    //no nulo y no opcional
    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "area_id",nullable = false)
    private Area area;

    public Carrera(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Carrera(String descripcion, String estado, Area area) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.area = area;
    }
}
