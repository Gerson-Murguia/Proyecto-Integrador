package edu.cibertec.murguia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FichaPostulacionDTO {

    private Long id;
    private double monto;
    private String nroPago;
    private String estado;

    private Long idModalidad;
    private String nombreModalidad;
    private Long idAdmision;
    private String nombreAdmision;
    private Long idPostulante;
    private String nombrePostulante;
    private Long idCarrera;
    private String nombreCarrera;
    private String fechaRegistro;

}
