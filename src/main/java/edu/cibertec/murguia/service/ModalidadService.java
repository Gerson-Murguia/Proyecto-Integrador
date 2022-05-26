package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Modalidad;

import java.util.List;

public interface ModalidadService {
    List<Modalidad> getModalidades();
    Modalidad getModalidad(Long id);
    Modalidad registrarModalidad(Modalidad modalidad);
    Modalidad actualizarModalidad(Long id,Modalidad modalidadRequest);
    void eliminarModalidad(Long id);
}
