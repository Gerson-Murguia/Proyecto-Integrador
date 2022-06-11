package edu.cibertec.murguia.controller;

import edu.cibertec.murguia.dto.VerificacionDTO;
import edu.cibertec.murguia.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static edu.cibertec.murguia.constant.ImageConstant.FORWARD_SLASH;
import static edu.cibertec.murguia.constant.ImageConstant.USER_FOLDER;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/imagen")
public class ImageController {

    private final VerificationService verificationService;

    //pasar parametro id de estudiante para buscar imagen fuente en bd?
    @PostMapping("/verify/{id}")
    public ResponseEntity<VerificacionDTO> verify(@PathVariable Long id,@RequestPart MultipartFile image) throws IOException {

        VerificacionDTO verify = verificationService.verify(id,image);

        //retornar si las imagenes se parecen o no, junto a la imagen de origen y la nueva
        return new ResponseEntity<>(verify, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> uploadImage(@RequestPart MultipartFile image,@RequestParam Long studentId) {
        System.out.println("StudentId: "+studentId);
        System.out.println("Uploading image...");
        System.out.println(image.getOriginalFilename());
        System.out.println(image.getContentType());
        System.out.println(image.getSize());

        return ResponseEntity.ok().build();
    }

    //seleccionar la imagen ya creada desde el sistema
    @GetMapping(value = "/image/profile/{name}/{fileName}",produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("name") String username,
                                  @PathVariable("fileName") String fileName) throws IOException {

        //user.home/user/Gerson/asd.jpg
        return Files.readAllBytes(Paths.get(USER_FOLDER+username+FORWARD_SLASH+fileName));
    }
}

