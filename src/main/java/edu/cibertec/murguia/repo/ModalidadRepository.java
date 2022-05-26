package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.Modalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadRepository extends JpaRepository<Modalidad, Long> {

}
