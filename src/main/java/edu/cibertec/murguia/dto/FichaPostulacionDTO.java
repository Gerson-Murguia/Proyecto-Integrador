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
    private Long idAdmision;
    private Long idPostulante;
    private Long idCarrera;
    private String fechaRegistro;

}
