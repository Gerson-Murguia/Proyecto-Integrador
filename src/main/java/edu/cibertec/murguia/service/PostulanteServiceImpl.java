package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Postulante;
import edu.cibertec.murguia.repo.PostulanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public List<Postulante> getPostulants() {
        return studentRepo.findAll();
    }

    @Override
    public Postulante getPostulante(Long id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public Postulante updatePostulante(long id, String name, String lastName, String email, String phone, String imageUrl) {
        Postulante postulante = studentRepo.findById(id).get();
        postulante.setName(name);
        postulante.setLastName(lastName);
        postulante.setEmail(email);
        postulante.setPhone(phone);
        postulante.setImageUrl(imageUrl);
        return postulante;
    }

    @Override
    public Postulante addNuevoPostulante(String name, String lastName, String email, String phone, String imageUrl) {
        Postulante postulante = new Postulante(name, lastName, email, phone, imageUrl);
        return studentRepo.save(postulante);
    }

    @Override
    public void eliminarPostulante(long id) {
        studentRepo.deleteById(id);
    }

	@Override
	public void guardarimg(Postulante postulante, MultipartFile imgpostulante) {
		
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
