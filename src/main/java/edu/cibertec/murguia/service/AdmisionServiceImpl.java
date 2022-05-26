package edu.cibertec.murguia.service;

import edu.cibertec.murguia.model.Admision;
import edu.cibertec.murguia.repo.AdmisionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AdmisionServiceImpl implements AdmisionService {

    private final AdmisionRepository admisionRepo;

    @Override
    public List<Admision> getAdmisiones() {
        return admisionRepo.findAll();
    }

    @Override
    public Admision getAdmision(Long id) {

        return admisionRepo.findById(id).get();
    }

    @Override
    public Admision registrarAdmision(Admision admision) {

        return admisionRepo.save(admision);
    }

    @Override
    public Admision updateAdmision(Long id, Admision admisionRequest) {
        Admision admision = admisionRepo.findById(id).get();
        admision.setDescripcion(admisionRequest.getDescripcion());
        admision.setEstado(admisionRequest.getEstado());
        return admision;
    }

    @Override
    public void deleteAdmision(Long studentId) {
        Admision admision = admisionRepo.findById(studentId).get();
        admisionRepo.delete(admision);
        //TODO: retornar la admision borrada?
    }
}

