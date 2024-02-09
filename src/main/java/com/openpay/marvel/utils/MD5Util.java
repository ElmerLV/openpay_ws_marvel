package com.openpay.marvel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static String encodeMD5(String input) {
        StringBuilder hexString = new StringBuilder("");

        try {
            // Crear una instancia de MessageDigest con el algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Actualizar el mensaje del resumen con la cadena de entrada
            md.update(input.getBytes());

            // Calcular el resumen de mensaje (el MD5)
            byte[] digest = md.digest();

            // Convertir el resumen de mensaje de bytes a hexadecimal
            hexString = new StringBuilder();
            for (byte b : digest) {
                // Convertir cada byte a su representación hexadecimal y agregarlo al StringBuilder
                hexString.append(String.format("%02x", b & 0xff));
            }

            // Imprimir el MD5 en forma de cadena hexadecimal
            System.out.println("MD5 de '" + input + "': " + hexString.toString());

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritmo no soportado: " + e.getMessage());
        }
        return hexString.toString();
    }
}

