package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Postulante;
import edu.cibertec.murguia.repo.PostulanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static edu.cibertec.murguia.constant.ImageConstant.IMAGE_BASE_URL;
import static edu.cibertec.murguia.constant.ImageConstant.USER_FOLDER;
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
    public void guardarimg(Postulante postulante, MultipartFile imgpostulante) throws IOException {
        System.out.println("imgpostulante: " + imgpostulante.getOriginalFilename());
        System.out.println("User folder: " + System.getProperty("user.home"));
        if(!imgpostulante.isEmpty()) {
            if (!Arrays.asList(IMAGE_JPEG_VALUE,IMAGE_PNG_VALUE,IMAGE_GIF_VALUE).contains(imgpostulante.getContentType())){
                log.info(imgpostulante.getOriginalFilename()+"no es una imagen con formato aceptado. Por favor, suba una imagen en formato jpg");
            }
            //System.getProperty("user.home")+"/verificacion/postulante/
            Path userFolder = Paths.get(USER_FOLDER).toAbsolutePath().normalize();
            if (!Files.exists(userFolder)){
                var file=Files.createDirectories(userFolder);
                //System.getProperty("user.home")+"/verificacion/postulante/
                log.info("Directorio creado:"+file.toString());
            }else{
                log.info("Directorio existente:"+userFolder.toString());
            }
            //System.getProperty("user.home")+"/verificacion/postulante/1.jpg"
            Files.deleteIfExists(Paths.get(userFolder+postulante.getId().toString()+".jpg"));
            //System.getProperty("user.home")+"/verificacion/postulante/1.jpg"
            Files.copy(imgpostulante.getInputStream(),userFolder.resolve(postulante.getId()+".jpg"),REPLACE_EXISTING);
            postulante.setImageUrl(setProfileImageUrl(postulante.getId()));
            studentRepo.save(postulante);
        }
    }

    private String setProfileImageUrl(Long id) {
        // /verificacion/postulante/1.jpg
        System.out.println("Set profile image url: "+ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(IMAGE_BASE_URL+id+".jpg")
                .toUriString());
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(IMAGE_BASE_URL+id+".jpg")
                .toUriString();
    }

    @Override
    public byte[] getProfileImage(Long id) throws IOException {
        System.out.println("Get profile image: "+Paths.get(System.getProperty("user.home")).toAbsolutePath().normalize()+"/"+id+".jpg");
        ///verificacion/postulante/1.jpg
        return Files.readAllBytes(Paths.get(USER_FOLDER+id+".jpg"));
    }
}
