package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.dto.FichaPostulacionDTO;
import edu.cibertec.murguia.model.FichaPostulacion;
import edu.cibertec.murguia.service.FichaPostulacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ficha")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class FichaController {

    private final FichaPostulacionService fichaPostulacionService;

    @GetMapping("/listado")
    public ResponseEntity<List<FichaPostulacion>> ListarFichaPostulacion(){

        List<FichaPostulacion> fichaPostulacion = fichaPostulacionService.getFichasPostulacion();
        if (!fichaPostulacion.isEmpty()) {
            return new ResponseEntity<>(fichaPostulacion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FichaPostulacion> getFichaPostulacion(@PathVariable Long id){
        FichaPostulacion fichaPostulacion = fichaPostulacionService.getFichaPostulacion(id);
        return new ResponseEntity<>(fichaPostulacion, HttpStatus.FOUND);
    }

    @PostMapping("/registrar")
    public ResponseEntity<FichaPostulacion> registrarFichaPostulacion(@RequestBody FichaPostulacionDTO fichaPostulacionDTO){
        FichaPostulacion fichaPostulacion = fichaPostulacionService.registrarFichaPostulacion(fichaPostulacionDTO);
        return new ResponseEntity<>(fichaPostulacion, HttpStatus.OK);
    }
    @PutMapping("/actualizar/{idFicha}")
    public ResponseEntity<FichaPostulacion> actualizarFichaPostulacion(@PathVariable Long idFicha,@RequestBody FichaPostulacionDTO fichaPostulacionDTO){
        FichaPostulacion fichaPostulacion = fichaPostulacionService.actualizarFichaPostulacion( idFicha,fichaPostulacionDTO);
        return new ResponseEntity<>(fichaPostulacion, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<FichaPostulacion> eliminarFichaPostulacion(@PathVariable Long id){
       fichaPostulacionService.eliminarFichaPostulacion(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
