package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Postulante;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostulanteService {
    //CRUD
    List<Postulante> getPostulants();
    Postulante getPostulante(Long id);
    Postulante updatePostulante(long id, String name, String lastName, String email, String phone, String imageUrl);
    Postulante addNuevoPostulante(String name, String lastName, String email, String phone, String imageUrl);
    void guardarimg(Postulante postulante, MultipartFile imgpostulante);
    void eliminarPostulante(long id);
}
