package edu.cibertec.murguia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="postulante")
public class Postulante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String studentId;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String imageUrl;
    //muchos estudiantes pueden tener el mismo examen
    @ManyToOne(fetch = FetchType.LAZY)
    private Exam exam;
    
    public Postulante(String name, String lastName, String email, String phone, String imageUrl) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }
}
