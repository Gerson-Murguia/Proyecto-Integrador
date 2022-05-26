package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Admision;

import java.util.List;

public interface AdmisionService {
    List<Admision> getAdmisiones();
    Admision getAdmision(Long id);
    Admision registrarAdmision(Admision admision);
    Admision updateAdmision(Long id, Admision admision);
    void deleteAdmision(Long studentId);
}
