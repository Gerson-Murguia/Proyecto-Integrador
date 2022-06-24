package edu.cibertec.murguia.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    //gestion de userDetails
    //crear tabla propia para los roles?
    private String role;//ROLE_USER{read,edit},ROLE_ADMIN{delete}
    private String[] authorities;//read,edit,delete,create
    private boolean isActive;
    private boolean isNotLocked;
}
