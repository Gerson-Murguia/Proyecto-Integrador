package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.AppUser;

import java.io.IOException;
import java.util.List;

public interface UserService {
    //OPERACIONES CRUD
    AppUser registro(String firstName, String lastName, String username, String email);
    List<AppUser> getUsers();
    // Por si se necesita agregar un usuario desde dentro de la app
    // AppUser addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage);
    AppUser updateUser(String currentUsername,String nuevoFirstName, String nuevoLastName, String nuevoUsername, String nuevoEmail);
    void deleteUser(String username) throws IOException;

    //BUSQUEDA
    AppUser findUserByUsername(String username);
    AppUser findUserByEmail(String email);
    //void resetPassword(String email);
}
