package edu.cibertec.murguia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="postulante")
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //ya fue, usar el id nomas, si no causara confusion
    //@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long id;
    //campo de uso interno, identificador unico
    private String studentId;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String imageUrl;
    //relacion con la FichaPostulacion
    // mappedBy es el nombre de la columna en la tabla FichaPostulacion (Postulante postulante)
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "postulante")
    @ToString.Exclude
    private List<FichaPostulacion> fichaPostulaciones;

    public Postulante(String name, String lastName, String email, String phone, String imageUrl) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Postulante that = (Postulante) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
