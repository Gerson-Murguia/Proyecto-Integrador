package edu.cibertec.murguia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @OneToOne(optional = true)
    @JoinColumn(name="id_modalidad")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Modalidad modalidad;
    @OneToOne(optional = true)
    @JsonIgnore
    @JoinColumn(name="id_admision")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Admision admision;
    //Muchas FichaPostulacion pueden pertenecer a un solo Postulante
    @ManyToOne(optional = false)
    @JoinColumn(name="id_postulante",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Postulante postulante;
    @OneToOne(optional = true)
    @JoinColumn(name="id_carrera")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Carrera carrera;
    //cambiar luego por localDate
    private String fechaRegistro;

}
