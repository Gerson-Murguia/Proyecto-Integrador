package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.model.Area;
import edu.cibertec.murguia.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    /**
     * Obtiene todas las areas
     * @return List<Area>
     */
    @GetMapping("/listado")
    public ResponseEntity<List<Area>> getAreas() {
        List<Area> areas = areaService.getAreas();
        if (areas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(areaService.getAreas());
    }
    /**
     * Obtiene una area
     * @param id id de la area
     * @return Area obtenida
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Area>> getAreaById(@PathVariable Long id) {
        Optional<Area> area = areaService.getArea(id);
        if (!area.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(area);
    }
    /**
     * Crea una area
     * @param area area a crear
     * @return Area creada
     */
    @PostMapping("/registrar")
    public ResponseEntity<Area> registrarArea(@RequestBody Area area) {
        Area areaRegistrada = areaService.registrarArea(area.getDescripcion(), area.getEstado());
        return new ResponseEntity<>(areaRegistrada, HttpStatus.CREATED);
    }
    /**
     * Actualiza una area
     * @param id id de la area
     * @param area area a actualizar
     * @return Area actualizada
     */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Area> actualizarArea(@RequestBody Area area, @PathVariable Long id) {
        Area areaActualizada = areaService.actualizarArea(id,area.getDescripcion(), area.getEstado());
        if (areaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(areaActualizada);
    }
    /**
     * Elimina una area
     * @param id id de la area
     * @return Area eliminada
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Area> eliminarArea(@PathVariable Long id) {
        areaService.eliminarArea(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Elimina TODAS las areas
     * @return Area eliminada
     */
    @DeleteMapping("/eliminar")
    public ResponseEntity<Area> eliminarTodasAreas() {
        areaService.eliminarTodasAreas();
        return ResponseEntity.ok().build();
    }

}
