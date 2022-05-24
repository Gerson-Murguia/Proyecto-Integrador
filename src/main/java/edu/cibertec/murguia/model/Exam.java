package edu.cibertec.murguia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="modalidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    private Long id;
    private String area;
    /**El examen solo puede ser de una carrera
    one to one*/
    @OneToOne
    private Carrera carrera;
    /**El examen solo puede tener una modalidad
    one to one
    El examen podria tener muchas modalidades?, se descarta para dar simplicidad.*/
    @OneToOne
    private Modality modalidad;
    //cambiar luego por localDate
    private String fecha;

    //un examen puede tener muchos postulantes
    //relation
    @OneToMany(mappedBy = "exam")
    private List<Postulant> postulants;
}
