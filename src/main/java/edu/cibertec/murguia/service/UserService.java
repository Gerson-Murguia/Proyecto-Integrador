package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.AppUser;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {
    //OPERACIONES CRUD
    AppUser registro(String firstName, String lastName, String username, String email) throws MessagingException;
    List<AppUser> getUsers();
    // Por si se necesita agregar un usuario desde dentro de la app
    // AppUser addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage);
    AppUser updateUser(String currentUsername,String nuevoFirstName, String nuevoLastName, String nuevoUsername, String nuevoEmail);
    void deleteUser(String username);

    //BUSQUEDA
    AppUser findUserByUsername(String username);
    AppUser findUserByEmail(String email);
    //void resetPassword(String email);
}
