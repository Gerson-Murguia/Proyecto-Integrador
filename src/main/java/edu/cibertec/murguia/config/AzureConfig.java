package edu.cibertec.murguia.config;

import com.microsoft.azure.cognitiveservices.vision.faceapi.models.AzureRegions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AzureConfig {

    public static String key;
    public static AzureRegions region=AzureRegions.EASTUS;

    public AzureConfig(@Value("${azure.cognitive.key}") String key){
        this.key = key;
    }
}
