package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    List<Carrera> findAllByAreaId(Long areaId);

    void deleteByAreaId(Long areaId);
}

