package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Area;

import java.util.List;
import java.util.Optional;

public interface AreaService {
    List<Area> getAreas();
    Optional<Area> getArea(Long id);
    Area registrarArea(String nombre, String descripcion);
    Area actualizarArea(Long id, String nombre, String descripcion);
    void eliminarArea(Long id);

    void eliminarTodasAreas();
}
