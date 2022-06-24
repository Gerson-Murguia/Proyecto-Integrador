package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Area;
import edu.cibertec.murguia.repo.AreaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepo;

    @Override
    public List<Area> getAreas() {

        return areaRepo.findAll();
    }

    @Override
    public Optional<Area> getArea(Long id) {
        return areaRepo.findById(id);
    }

    @Override
    public Area registrarArea(String descripcion, String estado) {

        return areaRepo.save(new Area(descripcion,estado));
    }

    @Override
    public Area actualizarArea(Long id, String descripcion, String estado) {
        Area area = areaRepo.findById(id).get();
        area.setDescripcion(descripcion);
        area.setEstado(estado);
        return area;
    }

    @Override
    public void eliminarArea(Long id) {
        areaRepo.deleteById(id);
    }

    @Override
    public void eliminarTodasAreas() {
        areaRepo.deleteAll();
    }
}

