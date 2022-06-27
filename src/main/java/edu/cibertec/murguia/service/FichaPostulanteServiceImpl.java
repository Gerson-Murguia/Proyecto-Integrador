package edu.cibertec.murguia.service;

import edu.cibertec.murguia.dto.FichaPostulacionDTO;
import edu.cibertec.murguia.model.FichaPostulacion;
import edu.cibertec.murguia.repo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class FichaPostulanteServiceImpl implements FichaPostulacionService {

    private final FichaPostulacionRepository fichaPostulacionRepo;
    private final PostulanteRepository postulanteRepo;
    private final AdmisionRepository admisionRepo;
    private final ModalidadRepository modalidadRepo;
    private final CarreraRepository carreraRepo;

    @Override
    public List<FichaPostulacion> getFichasPostulacion() {

        return fichaPostulacionRepo.findAll();
    }

    @Override
    public FichaPostulacion getFichaPostulacion(Long id) {

        return fichaPostulacionRepo.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la ficha de postulacion"));
    }

    //TODO: Cambiar parametros
    @Override
    public FichaPostulacion registrarFichaPostulacion(FichaPostulacionDTO fichaPostulacionDTO) {

        FichaPostulacion fichaPostulacion = new FichaPostulacion();
        fichaPostulacion.setId(fichaPostulacionDTO.getId());
        fichaPostulacion.setNroPago(fichaPostulacionDTO.getNroPago());
        fichaPostulacion.setMonto(fichaPostulacionDTO.getMonto());
        fichaPostulacion.setEstado(fichaPostulacionDTO.getEstado());
        fichaPostulacion.setFechaRegistro(LocalDate.now().toString());
        fichaPostulacion.setPostulante(postulanteRepo.findById(fichaPostulacionDTO.getIdPostulante()).get());
        fichaPostulacion.setAdmision(admisionRepo.findById(fichaPostulacionDTO.getIdAdmision()).get());
        fichaPostulacion.setModalidad(modalidadRepo.findById(fichaPostulacionDTO.getIdModalidad()).get());
        fichaPostulacion.setCarrera(carreraRepo.findById(fichaPostulacionDTO.getIdCarrera()).get());

        return fichaPostulacionRepo.save(fichaPostulacion);
    }

    @Override
    public FichaPostulacion actualizarFichaPostulacion(Long idFicha,FichaPostulacionDTO fichaPostulacionDTO) {
        FichaPostulacion fichaPostulacion = fichaPostulacionRepo.findById(idFicha).orElseThrow(() -> new RuntimeException("No se encontro la ficha de postulacion"));
        fichaPostulacion.setNroPago(fichaPostulacionDTO.getNroPago());
        fichaPostulacion.setMonto(fichaPostulacionDTO.getMonto());
        fichaPostulacion.setEstado(fichaPostulacionDTO.getEstado());
        //fichaPostulacion.setFechaRegistro(LocalDate.now().toString());
        fichaPostulacion.setPostulante(postulanteRepo.getById(fichaPostulacionDTO.getIdPostulante()));
        fichaPostulacion.setAdmision(admisionRepo.getById(fichaPostulacionDTO.getIdAdmision()));
        fichaPostulacion.setModalidad(modalidadRepo.getById(fichaPostulacionDTO.getIdModalidad()));
        fichaPostulacion.setCarrera(carreraRepo.getById(fichaPostulacionDTO.getIdCarrera()));
        return fichaPostulacionRepo.save(fichaPostulacion);
    }

    @Override
    public void eliminarFichaPostulacion(Long id) {
        fichaPostulacionRepo.deleteById(id);
    }
}

