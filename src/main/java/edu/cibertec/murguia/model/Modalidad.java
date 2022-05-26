package edu.cibertec.murguia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name="modalidad")
@NoArgsConstructor
@AllArgsConstructor
public class Modalidad {
    //id,nombre (ordinario,extraordinario,etc)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String estado;

    public Modalidad(String descripcion, String estado) {
        this.descripcion =  descripcion;
        this.estado = estado;
    }
    //la relacion con ficha de postulacion se mapea en la clase FichaPostulacion
}