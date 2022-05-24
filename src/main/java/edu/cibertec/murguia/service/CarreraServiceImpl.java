package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Carrera;
import edu.cibertec.murguia.repo.AreaRepository;
import edu.cibertec.murguia.repo.CarreraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CarreraServiceImpl implements CarreraService {

    private final CarreraRepository carreraRepo;
    private final AreaRepository areaRepo;
    @Override
    public List<Carrera> getCarreras() {

        return carreraRepo.findAll();
    }

    @Override
    public Carrera getCarrera(Long id) {
        //TODO: En lugar de una excepcion, devolver un objeto personalizado Response
        return carreraRepo.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la carrera"));
    }

    @Override
    public Carrera registrarCarrera(Long areaId,Carrera carrera) {
        Carrera carreraCreada= areaRepo.findById(areaId).map(area -> {
            carrera.setArea(area);
            return carreraRepo.save(carrera);
        }).orElseThrow(() -> new RuntimeException("No se encontro el area con id: " + areaId));
        return carreraCreada;
    }

    @Override
    public Carrera actualizarCarrera(Long id, Carrera nuevaCarrera) {
        //buscar Area
        Carrera carrera= carreraRepo.findById(id).get();
        carrera.setDescripcion(nuevaCarrera.getDescripcion());
        carrera.setEstado(nuevaCarrera.getEstado());
        carreraRepo.save(carrera);
        return carrera;
    }

    @Override
    public List<Carrera> findAllByAreaId(Long idarea) {

        return carreraRepo.findAllByAreaId(idarea);
    }

    @Override
    public void eliminarCarrera(Long id) {
        carreraRepo.deleteById(id);
        //TODO: Devolver la carrera eliminada?
    }
}

