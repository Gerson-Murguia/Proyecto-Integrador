package edu.cibertec.murguia.service;

import edu.cibertec.murguia.dto.FichaPostulacionDTO;
import edu.cibertec.murguia.model.FichaPostulacion;

import java.util.List;

public interface FichaPostulacionService {
    List<FichaPostulacion> getFichasPostulacion();
    FichaPostulacion getFichaPostulacion(Long id);
    FichaPostulacion registrarFichaPostulacion(FichaPostulacionDTO fichaPostulacionDTO);
    FichaPostulacion actualizarFichaPostulacion(Long idFicha,FichaPostulacionDTO fichaPostulacionDTO);
    void eliminarFichaPostulacion(Long id);

    //TODO: agregar mas metodos
}
