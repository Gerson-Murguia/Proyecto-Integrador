package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Postulant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostulanteService {
    //CRUD
    List<Postulant> getPostulants();
    Postulant getPostulante(Long id);
    Postulant updatePostulante(long id,String name, String lastName, String email, String phone, String imageUrl);
    Postulant addNuevoPostulante(String name, String lastName, String email, String phone, String imageUrl);
    void guardarimg(Postulant postulante, MultipartFile imgpostulante);
    void eliminarPostulante(long id);
}
