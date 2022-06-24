package edu.cibertec.murguia.service;

import edu.cibertec.murguia.config.UserPasswordEncoder;
import edu.cibertec.murguia.model.AppUser;
import edu.cibertec.murguia.model.AppUserDetails;
import edu.cibertec.murguia.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static edu.cibertec.murguia.enumeration.Role.ROLE_USER;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepo;
    private final UserPasswordEncoder userPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user= userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }else{
            userRepo.save(user);
            AppUserDetails userDetails = new AppUserDetails(user);
            log.info("Returnando user details para el usuario {}", user.getUsername());
            return userDetails;
        }
    }
    @Override
    public AppUser registro(String firstName, String lastName, String username, String email) {
        //TValidar que el usuario no exista y que el email sea válido
        validateUser(StringUtils.EMPTY, username, email);
        //Datos de usuario
        AppUser user = new AppUser();
        //id secundaria, no la autogenerada
        user.setUserId(generateUserId());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);

        //la contraseña no la genera el usuario, la genera el sistema
        String password=generatePassword();
        String encodedPassword=userPasswordEncoder.passwordEncoder().encode(password);
        user.setPassword(encodedPassword);

        //campos de spring security
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(ROLE_USER.name());
        user.setAuthorities(ROLE_USER.getAuthorities());
        //TODO: Agregar campo de imagen de perfil

        //TODO: Enviar email de confirmacion con javaxmail
        //por el momento imprimir la contraseña en concola
        System.out.println("La contraseña generada es: "+password);
        return userRepo.save(user);
    }

    private String generatePassword() {
        //genera una contraseña aleatoria de 10 caracteres
        return RandomStringUtils.randomAlphanumeric(10);
    }

    @Override
    public List<AppUser> getUsers() {return userRepo.findAll();}

    @Override
    public AppUser updateUser(String currentUsername, String nuevoFirstName, String nuevoLastName, String nuevoUsername, String nuevoEmail) {
        //TODO: Validar que el usuario exista y lanzar excepcion personalizada
        AppUser currentUser=validateUser(currentUsername,nuevoUsername,nuevoEmail).orElseThrow(()->new RuntimeException("El usuario no existe"));
        currentUser.setFirstName(nuevoFirstName);
        currentUser.setLastName(nuevoLastName);
        currentUser.setUsername(nuevoUsername);
        currentUser.setEmail(nuevoEmail);
        //TODO: Agregar y setear campos de spring security
        return userRepo.save(currentUser);
    }

    private Optional<AppUser> validateUser(String currentUsername, String nuevoUsername, String nuevoEmail) {
        AppUser userByNewUsername=findUserByUsername(nuevoUsername);
        AppUser userByNewEmail=findUserByEmail(nuevoEmail);
        if (StringUtils.isNotBlank(currentUsername)){
            AppUser currentUser=findUserByUsername(currentUsername);
            if (currentUser==null){
                //TODO: Lanzar excepcion personalizada de UserNotFound
                return Optional.empty();
            }

            if (userByNewUsername!=null && !userByNewUsername.getUserId().equals(currentUser.getUserId())){
                //TODO: Lanzar excepcion personalizada de usernameAlreadyExists
                return Optional.empty();
            }
            if (userByNewEmail!=null && !userByNewEmail.getUserId().equals(currentUser.getUserId())){
                //TODO: Lanzar excepcion personalizada de emailAlreadyExists
                return Optional.empty();
            }
            return Optional.of(currentUser);
        }else{
            if (userByNewUsername!=null){
                //TODO: Lanzar excepcion personalizada de usernameAlreadyExists
            }
            if (userByNewEmail!=null){
                //TODO: Lanzar excepcion personalizada de emailAlreadyExists
            }
            return Optional.empty();
        }
    }

    @Override
    public void deleteUser(String username) {
        AppUser user = userRepo.findByUsername(username);
        //Implementar borrar carpeta de imagen de perfil si es que se llega a agregar la funcionalidad
        userRepo.delete(user);
    }
    /**
     * Encuentra un usuario por su username
     * */
    @Override
    public AppUser findUserByUsername(String username) {return userRepo.findByUsername(username);}

    /**
     * Encuentra un usuario por su email
     * */
    @Override
    public AppUser findUserByEmail(String email) {return userRepo.findByEmail(email);}

    /**
     * Genera un id de usuario aleatorio
     * */
    private String generateUserId() {
        return RandomStringUtils.randomNumeric(10);
    }


}
