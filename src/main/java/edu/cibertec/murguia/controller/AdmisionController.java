package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.model.Admision;
import edu.cibertec.murguia.service.AdmisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admision")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdmisionController {

    private final AdmisionService admisionService;

    /**
     * Obtiene todas las admisiones
     * @return List<Admision>
     */
    @GetMapping("/listado")
    public ResponseEntity<List<Admision>> getAdmisiones() {
        System.out.println("AdmisionController.getAdmisiones()");
        List<Admision> admisiones = admisionService.getAdmisiones();
        if (!admisiones.isEmpty()) {
            return ResponseEntity.ok(admisiones);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Obtiene una admision
     * @param id id de la admision
     * @return Admision obtenida
     */
    @GetMapping("/{id}")
    public ResponseEntity<Admision> getAdmision(@PathVariable Long id) {
        System.out.println("AdmisionController.getAdmision()");
        return new ResponseEntity<>(admisionService.getAdmision(id), HttpStatus.OK);
    }

    /**
     * Crea una admision
     * @param admisionRequest admision a crear
     * @return Admision creada
     */
    @PostMapping("/registrar")
    public ResponseEntity<Admision> registrarAdmision(@RequestBody Admision admisionRequest) {
        System.out.println("AdmisionController.registrarAdmision()");
        return new ResponseEntity<>(admisionService.registrarAdmision(admisionRequest), HttpStatus.OK);
    }

    /**
     * Actualiza una admision
     * @param id id de la admision
     * @param admisionRequest admision a actualizar
     * @return Admision actualizada
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Admision> updateAdmision(@PathVariable Long id, @RequestBody Admision admisionRequest) {
        System.out.println("AdmisionController.updateAdmision()");
        return new ResponseEntity<>(admisionService.updateAdmision(id, admisionRequest), HttpStatus.OK);
    }

    /**
     * Elimina una admision
     * @param id id de la admision
     * @return Admision eliminada
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteAdmision(@PathVariable Long id) {
        System.out.println("AdmisionController.deleteAdmision()");
        admisionService.deleteAdmision(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
