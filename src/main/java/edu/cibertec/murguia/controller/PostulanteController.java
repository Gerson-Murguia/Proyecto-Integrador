package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.model.Postulante;
import edu.cibertec.murguia.service.PostulanteService;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/postulante")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PostulanteController {
	
    private final PostulanteService postulanteService;

	/**
	 * Obtiene todos los postulantes
	 * @return List<Postulante>
	 */
	@GetMapping("/listado")
	public ResponseEntity<List<Postulante>> ListarPostulante(){

		List<Postulante> postulante = postulanteService.getPostulants();
		if (!postulante.isEmpty()) {
			return new ResponseEntity<>(postulante, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * Obtiene un postulante
	 * @param id id del postulante
	 * @return Postulante obtenido
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Postulante> getPostulante(@PathVariable Long id){
		Postulante postulante = postulanteService.getPostulante(id);
		return new ResponseEntity<>(postulante, HttpStatus.FOUND);
	}

	@PostMapping("/registrar")
	public ResponseEntity<Postulante> guardarPostulante(@RequestParam("name") String name, @RequestParam("lastName") String lastName, @RequestParam("email") String email, @RequestParam("address") String address, @RequestParam MultipartFile image){
		
		Postulante nuevoPostulante=postulanteService.addNuevoPostulante(name, lastName, email, address, null);
		postulanteService.guardarimg(nuevoPostulante, image);
		
		return new ResponseEntity<>(nuevoPostulante,HttpStatus.CREATED);
	}

	@PostMapping("/editar/{id}")
	public ResponseEntity<Postulante> editarPostulante (@PathVariable long id , @RequestParam String name, @RequestParam String lastName, @RequestParam String email, @RequestParam String address, @RequestParam MultipartFile image){
		
		Postulante editarPostulante=postulanteService.updatePostulante(id,name, lastName, email, address, null);
		postulanteService.guardarimg(editarPostulante, image);
		return new ResponseEntity<>(editarPostulante,HttpStatus.OK);
	}


	@DeleteMapping("/eliminar/{id}")
	public void eliminarPostulante(@PathVariable long id){
		postulanteService.eliminarPostulante(id);
	}
	
	
	@GetMapping(value = "/image/profile/{id}",produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("id") Long id) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/"+id+".jpg"));
    }
	
	
}
