package edu.cibertec.murguia.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modalidad")
public class Modality {
    //id,nombre (ordinario,extraordinario,etc)
    @Id
    private Long id;
    private String nombre;
}
