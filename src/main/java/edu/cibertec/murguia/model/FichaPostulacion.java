package edu.cibertec.murguia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="ficha_postulacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaPostulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double monto;
    private String nroPago;
    private String estado;

    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="id_modalidad")
    private Modalidad modalidad;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="id_admision")
    private Admision admision;
    //Muchas FichaPostulacion pueden pertenecer a un solo Postulante
    @ManyToOne(cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name="id_postulante",nullable = false)
    private Postulante postulante;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name="id_carrera")
    private Carrera carrera;
    //cambiar luego por localDate
    private String fechaRegistro;

}
