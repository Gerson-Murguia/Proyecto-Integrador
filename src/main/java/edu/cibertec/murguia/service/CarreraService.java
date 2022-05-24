package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Carrera;

import java.util.List;

public interface CarreraService {
    List<Carrera> getCarreras();
    Carrera getCarrera(Long id);
    Carrera registrarCarrera(Long areaId,Carrera carrera);
    Carrera actualizarCarrera(Long id, Carrera nuevaCarrera);
    List<Carrera> findAllByAreaId(Long idarea);
    //devolver la carrera eliminada?
    void eliminarCarrera(Long id);
}
