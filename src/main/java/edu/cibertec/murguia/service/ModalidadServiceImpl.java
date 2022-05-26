package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Modalidad;
import edu.cibertec.murguia.repo.ModalidadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ModalidadServiceImpl implements ModalidadService {

    private final ModalidadRepository modalidadRepo;

    @Override
    public List<Modalidad> getModalidades() {
        return modalidadRepo.findAll();
    }

    @Override
    public Modalidad getModalidad(Long id) {
        //TODO: En lugar de una excepcion, devolver un objeto personalizado Response
        return modalidadRepo.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la modalidad"));
    }

    @Override
    public Modalidad registrarModalidad(Modalidad modalidad) {
        return modalidadRepo.save(modalidad);
    }

    @Override
    public Modalidad actualizarModalidad(Long id, Modalidad modalidadRequest) {
        Modalidad modalidad = modalidadRepo.findById(id).get();
        modalidad.setDescripcion(modalidadRequest.getDescripcion());
        modalidad.setEstado(modalidadRequest.getEstado());
        return modalidadRepo.save(modalidad);
    }

    @Override
    public void eliminarModalidad(Long id) {
        modalidadRepo.deleteById(id);
    }
}

