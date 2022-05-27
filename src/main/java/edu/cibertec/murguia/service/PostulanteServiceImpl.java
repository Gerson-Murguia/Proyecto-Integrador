package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Postulant;
import edu.cibertec.murguia.repo.PostulanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class PostulanteServiceImpl implements PostulanteService {
    private final PostulanteRepository studentRepo;

    @Override
    public List<Postulant> getPostulants() {
        return studentRepo.findAll();
    }

    @Override
    public Postulant getPostulante(Long id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public Postulant updatePostulante(long id, String name, String lastName, String email, String phone, String imageUrl) {
        Postulant postulante = studentRepo.findById(id).get();
        postulante.setName(name);
        postulante.setLastName(lastName);
        postulante.setEmail(email);
        postulante.setPhone(phone);
        postulante.setImageUrl(imageUrl);
        return postulante;
    }

    @Override
    public Postulant addNuevoPostulante(String name, String lastName, String email, String phone, String imageUrl) {
        Postulant postulante = new Postulant(name, lastName, email, phone, imageUrl);
        return studentRepo.save(postulante);
    }

    @Override
    public void eliminarPostulante(long id) {
        studentRepo.deleteById(id);
    }

	@Override
	public void guardarimg(Postulant postulante, MultipartFile imgpostulante) {
		
		if (!Arrays.asList(IMAGE_JPEG_VALUE,IMAGE_PNG_VALUE,IMAGE_GIF_VALUE).contains(imgpostulante.getContentType())){
			log.info(imgpostulante.getOriginalFilename()+"no es una imagen. Por favor, suba una imagen");
        }
		
		if(!imgpostulante.isEmpty()) {
			try {
				Path path = Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/images/").toAbsolutePath().normalize();
				/*if(!Files.exists(path)) {
					 Files.createDirectories(path);
					 log.info("Directorio creado:"+path.toString());
				}*/
				Files.deleteIfExists(Paths.get(path+".jpg"));
				Files.copy(imgpostulante.getInputStream(),path.resolve(postulante.getId()+".jpg"),REPLACE_EXISTING);
				//Files.write(path, bytes);
				postulante.setImageUrl(path.toString()+"\\"+postulante.getId()+".jpg");
				studentRepo.save(postulante);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
   
	

}
