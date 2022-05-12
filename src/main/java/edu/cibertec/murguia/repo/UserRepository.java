package edu.cibertec.murguia.repo;

import edu.cibertec.murguia.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);
}
