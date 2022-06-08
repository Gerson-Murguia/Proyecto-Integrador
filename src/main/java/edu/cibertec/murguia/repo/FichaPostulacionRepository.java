package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.FichaPostulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichaPostulacionRepository extends JpaRepository<FichaPostulacion, Long> {

}
