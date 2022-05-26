package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.model.Modalidad;
import edu.cibertec.murguia.service.ModalidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/modalidad")
public class ModalidadController {

    private final ModalidadService modalidadService;

    /**
     * Obtiene todas las modalidades
     * @return List<Modalidad>
     */
    @GetMapping("/listado")
    public ResponseEntity<List<Modalidad>> getModalidades() {
        System.out.println("ModalidadController.getModalidades()");
        List<Modalidad> modalidades = modalidadService.getModalidades();
        if (!modalidades.isEmpty()) {
            return new ResponseEntity<>(modalidades, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Obtiene una modalidad
     * @param id id de la modalidad
     * @return Modalidad obtenida
     */
    @GetMapping("/{id}")
    public ResponseEntity<Modalidad> getModalidad(@PathVariable Long id) {
        System.out.println("ModalidadController.getModalidad()");
        Modalidad modalidad = modalidadService.getModalidad(id);
        return new ResponseEntity<>(modalidad, HttpStatus.OK);
    }

    /**
     * Crea una modalidad
     * @param modalidadRequest modalidad a crear
     * @return Modalidad creada
     */
    @PostMapping("/registrar")
    public ResponseEntity<Modalidad> registrarModalidad(@RequestBody Modalidad modalidadRequest) {
        System.out.println("ModalidadController.registrarModalidad()");
        return new ResponseEntity<>(modalidadService.registrarModalidad(modalidadRequest), HttpStatus.OK);
    }

    /**
     * Actualiza una modalidad
     * @param id id de la modalidad a actualizar
     * @param modalidadRequest modalidad a actualizar
     * @return Modalidad actualizada
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Modalidad> actualizarModalidad(@PathVariable Long id, @RequestBody Modalidad modalidadRequest) {
        System.out.println("ModalidadController.actualizarModalidad()");
        Modalidad modalidad = modalidadService.actualizarModalidad(id, modalidadRequest);
        return new ResponseEntity<>(modalidad, HttpStatus.OK);
    }

    /**
     * Elimina una modalidad
     * @param id id de la modalidad a eliminar
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarModalidad(@PathVariable Long id) {
        System.out.println("ModalidadController.eliminarModalidad()");
        modalidadService.eliminarModalidad(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
