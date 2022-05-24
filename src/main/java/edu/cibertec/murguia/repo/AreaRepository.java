package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {
    Area findByDescripcionIgnoreCase(String descripcion);
    //encontrar area por estado
    Area findByEstadoIgnoreCase(String estado);
}
