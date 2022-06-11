package edu.cibertec.murguia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificacionDTO {
    private String porcentajeConfianza; //70%
    private boolean isIdentical;
    private String descripcion;
}
