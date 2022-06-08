package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Postulante, Long> {

    Postulante findStudentByEmail(String email);
    Postulante findStudentByLastName(String lastName);
}

