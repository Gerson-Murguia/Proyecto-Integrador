package edu.cibertec.murguia.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name="carrera")
public class Career {
    //id,exam
    @Id
    private Long id;
    private String name;
    private String duration;
    private int credits;
    private int semesters;

    //El examen referencia una carrera, no viceversa
}
