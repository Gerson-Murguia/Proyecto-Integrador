package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.model.AppUser;
import edu.cibertec.murguia.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/usuario")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
   private final UserService userService;
   private final AuthenticationManager authenticationManager;

   @PostMapping("/registro")
   public ResponseEntity<AppUser> register(@RequestBody AppUser user) throws MessagingException {
      AppUser userRegistro=userService.registro(user.getFirstName(),user.getLastName(),user.getUsername(),user.getEmail());
      return new ResponseEntity<>(userRegistro, HttpStatus.OK);
   }

   @PostMapping("/login")
   public ResponseEntity<AppUser> login(@RequestBody AppUser user)  {
      System.out.println("Ingreso a login");
      //validacion
      authenticate(user.getUsername(),user.getPassword());
      AppUser loginUser=userService.findUserByUsername(user.getUsername());
      System.out.println("Usuario logueado: "+loginUser.getUsername());
      return new ResponseEntity<>(loginUser, HttpStatus.OK);
   }
   private void authenticate(String username, String password) {
      System.out.println("Ingreso a authenticate");
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
   }

   //user crud operations
   @GetMapping("/{username}")
   public ResponseEntity<AppUser> getUser(@PathVariable String username) {
      AppUser user = userService.findUserByUsername(username);
      return new ResponseEntity<>(user, HttpStatus.OK);
   }

   @GetMapping("/")
   public ResponseEntity<List<AppUser>> getUsers() {
      List<AppUser> users = userService.getUsers();
      return new ResponseEntity<>(users, HttpStatus.OK);
   }

   @DeleteMapping("/{username}")
   public ResponseEntity<Void> deleteUser(@PathVariable String username) throws IOException {
      userService.deleteUser(username);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
}
