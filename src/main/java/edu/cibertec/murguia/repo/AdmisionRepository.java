package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.Admision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmisionRepository extends JpaRepository<Admision, Long> {

}
