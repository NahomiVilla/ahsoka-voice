package com.ashokavoice.ashokavoice;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateSecretKey {
    public static void main(String[] args) {
        // Generar una clave secreta de 32 bytes
        byte[] secretKey = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secretKey);

        // Codificar la clave secreta en Base64
        String secretKeyBase64 = Base64.getEncoder().encodeToString(secretKey);

        // Imprimir la clave secreta en Base64
        System.out.println("Clave secreta en Base64: " + secretKeyBase64);
    }
}
