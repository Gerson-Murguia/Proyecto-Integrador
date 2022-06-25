package edu.cibertec.murguia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoIntegradorApplication {

    /**
     * Este programa contendrá:
     * - Verificación de identidad: Con dos fotos de una persona, se verificará si son la misma persona.
     * La primera foto se obtendra de la base de datos, la segunda será tomada y subida por el usuario.
     *
     * Prerequisitos:
     * - La cuenta de Azure debe tener una subscripción a Face API(limitada a 20 llamadas por minuto).
     * - Crear un lib folder en el root del proyecto., luego añadir los jars necesarios.
     * - Descargar el FaceAPI library (ms-azure-cs-faceapi-1.0.jar) y añadirlo al lib folder.
     * - Reemplazar el valor de la variable region por la region de la cuenta de Azure.
     * Gerson Aldair Murguia Montes - programador backend
     *
     * **/

    public static void main(String[] args) {
        SpringApplication.run(ProyectoIntegradorApplication.class, args);
        System.out.println("Inicio");
    }
}
