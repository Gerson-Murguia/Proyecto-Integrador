package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.model.Carrera;
import edu.cibertec.murguia.service.AreaService;
import edu.cibertec.murguia.service.CarreraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/carrera")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CarreraController {
    private final CarreraService carreraService;
    private final AreaService areaService;

    /**
     * Obtiene todas las carreras de un area
     * @param areaId id del area de la que se obtienen las carreras
     * @return List<Carrera>
     */
    @GetMapping("/area/{areaId}")
    public ResponseEntity<List<Carrera>> findAllByAreaId(@PathVariable("areaId") Long areaId) {
        System.out.println("CarreraController.findAllByAreaId()");
        return new ResponseEntity<>(carreraService.findAllByAreaId(areaId), HttpStatus.OK);
    }

    /**
     * Obtiene todas las carreras
     * @return List<Carrera>
     */
    @GetMapping("/listado")
    public ResponseEntity<List<Carrera>> getCarreras() {
        System.out.println("CarreraController.getCarreras()");
        List<Carrera> carreras = carreraService.getCarreras();
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    /**
     * Obtiene una carrera
     * @param id id de la carrera
     * @return Carrera obtenida
     */
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarrera(@PathVariable Long id) {
        System.out.println("CarreraController.getCarrera()");
        return new ResponseEntity<>(carreraService.getCarrera(id), HttpStatus.OK);
    }

    /***
     * Crea una carrera perteneciente a un area
     * @param areaId area a la que pertenece la carrera
     * @param carreraRequest carrera a crear
     * @return Carrera creada
     */
    @PostMapping("/registrar/area/{areaId}")
    public ResponseEntity<Carrera> registrarCarrera(@PathVariable("areaId") Long areaId , @RequestBody Carrera carreraRequest) {
        System.out.println("CarreraController.registrarCarrera()");
        return new ResponseEntity<>(carreraService.registrarCarrera(areaId, carreraRequest), HttpStatus.CREATED);
    }

    /**
     * Actualiza una carrera
     * @param id id de la carrera a actualizar
     * @param nuevaCarrera carrera con los nuevos datos
     * @return Carrera actualizada
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Carrera> actualizarCarrera(@PathVariable Long id, Carrera nuevaCarrera) {
        System.out.println("CarreraController.actualizarCarrera()");
        Carrera carrera = carreraService.actualizarCarrera(id, nuevaCarrera);
        return new ResponseEntity<>(carrera, HttpStatus.OK);
    }

    /**
     * Elimina una carrera
     * @param id id de la carrera a eliminar
     * @return Carrera eliminada
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCarrera(@PathVariable Long id) {
        System.out.println("CarreraController.eliminarCarrera()");
        carreraService.eliminarCarrera(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
