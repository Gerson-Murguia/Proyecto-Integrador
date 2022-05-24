package edu.cibertec.murguia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="area")
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String descripcion;
    private String estado;
    //Una area tiene muchas carreras
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "area")
    @JsonIgnore
    private List<Carrera> carreras;

    public Area(String descripcion, String estado) {
        this.descripcion = descripcion;
        this.estado = estado;
    }
}
