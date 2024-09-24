package com.ipartek.auxiliares;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    public static String hash(String input) {
        try {
            // Crear una instancia de MessageDigest para SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convertir el input en bytes
            byte[] inputBytes = input.getBytes();

            // Ejecutar el hash y obtener los bytes hasheados
            byte[] hashedBytes = md.digest(inputBytes);

            // Convertir los bytes hasheados a formato hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            // Devolver el string en formato hexadecimal
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al calcular el hash", e);
        }
    }
}
