package edu.cibertec.murguia.config;

import com.microsoft.azure.cognitiveservices.vision.faceapi.models.AzureRegions;
import org.springframework.stereotype.Component;

@Component
public class AzureConfig {

    public static String key="5d19924693f2444eb58ab3523f6629c1";
    public static AzureRegions region=AzureRegions.EASTUS;

}
