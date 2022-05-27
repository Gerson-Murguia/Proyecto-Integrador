package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.Postulant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostulanteRepository extends JpaRepository<Postulant, Long> {

    Postulant findStudentByEmail(String email);
    Postulant findStudentByPhone(String phone);
}

