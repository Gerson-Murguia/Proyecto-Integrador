package edu.cibertec.murguia.service;

import com.microsoft.azure.cognitiveservices.vision.faceapi.FaceAPI;
import com.microsoft.azure.cognitiveservices.vision.faceapi.FaceAPIManager;
import com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectWithStreamOptionalParameter;
import com.microsoft.azure.cognitiveservices.vision.faceapi.models.DetectedFace;
import com.microsoft.azure.cognitiveservices.vision.faceapi.models.VerifyResult;
import edu.cibertec.murguia.config.AzureConfig;
import edu.cibertec.murguia.dto.VerificacionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class VerificationService {

    /**
     * Autenticación de la cuenta de Azure.
     */
    // Añadir la región del Face subscription.
    // Lista de Azure Regions: https://docs.microsoft.com/en-us/java/api/com.microsoft.azure.cognitiveservices.vision.faceapi.models.azureregions?view=azure-java-stable
    // Create Face client
    private FaceAPI client = FaceAPIManager.authenticate(AzureConfig.region, AzureConfig.key);
    /**
     * END - Autenticación de la cuenta de Azure.
     */
    private final PostulanteService postulanteService;

    public VerificationService(PostulanteService postulanteService) {
        this.postulanteService = postulanteService;
    }

    public VerificacionDTO verify(Long id,MultipartFile image) throws IOException {
        //TODO: Obtener imagen de la base de datos mediante el id de usuario
        byte[] sourceImage=postulanteService.getProfileImage(id);

        List<byte[]> targetImage=new ArrayList<>();
        //TODO: Obtener imagen desde el frontend
        targetImage.add(image.getInputStream().readAllBytes());

        //Detecta caras en la imagen de fuente;
        List<UUID> sourceFaceUuids=detectFaces(this.client,sourceImage);

        // Detecta las caras en las imagenes objetivo
        List<UUID> objetiveFaceUuids = detectFaces(this.client, targetImage.get(0));

        //Ejemplo de verificación para caras de una misma persona
        VerifyResult result = client.faces().verifyFaceToFace(sourceFaceUuids.get(0), objetiveFaceUuids.get(0));


        VerificacionDTO verificacion=new VerificacionDTO();
        if (result.isIdentical()){


            System.out.println("Las caras son de la misma persona");

            verificacion.setPorcentajeConfianza(result.confidence()*100+"%");
            verificacion.setIdentical(result.isIdentical());
            verificacion.setDescripcion("Las caras son de la misma persona");
        }else{
            verificacion.setPorcentajeConfianza(result.confidence()*100+"%");
            verificacion.setIdentical(result.isIdentical());
            verificacion.setDescripcion("Las caras no son de la misma persona");
        }

        //TODO: Borrar resultado temporal
        System.out.println(result.isIdentical() ?
                "Caras de " + sourceImage+ " y " + targetImage.get(0) + " son de la misma persona, con el : " + result.confidence()*100 + "% de similitud." :
                "Caras de " + sourceImage + " y " + targetImage.get(0) + " son de diferentes personas, con el : " + result.confidence()*100 + "% de similitud.");

        return verificacion;
    }

    /**
     * Detect Face:
     * Detecta la(s) caras(s) en un image URL.
     * @param client Face API client
     * @param image archivo de la imagen
     * @return Lista de UUID's de caras detectadas
     */
    private List<UUID> detectFaces(FaceAPI client, byte[] image) {
        // Crea lista de IDs de caras detectadas
        List<DetectedFace> facesList = client.faces().detectWithStream(image, new DetectWithStreamOptionalParameter().withReturnFaceId(true));
        System.out.println("ID(s) de cara detectada  desde URL image: ");
        // Obtener UUID(s) de caras(s) y mostrarlo en la consola
        List<UUID> faceUuids = new ArrayList<>();
        for (DetectedFace face : facesList) {
            faceUuids.add(face.faceId());
            System.out.println(face.faceId());
        }
        System.out.println("Caras detectadas: " + faceUuids.size());
        return faceUuids;
    }
    /**
     * END - Detect Face
     */
}
