package com.company.eg007.keyderivativefunction;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;


//Key Derivation Function API :

public class KDFDemo {
    public static void main(String[] args) throws Exception {
        // 1️⃣ Password provided by user
        char[] password = "javatechie".toCharArray();

        // 2️⃣ Generate a random salt for extra security
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        // 3️⃣ PBKDF2 parameters: password, salt, iterations, key length
        PBEKeySpec spec = new PBEKeySpec(password, salt, 65536, 256);

        // 4️⃣ Create the key factory and generate the derived key
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey derivedKey = factory.generateSecret(spec);

        // 5️⃣ Show results
        System.out.println("Derived key (hex): " + bytesToHex(derivedKey.getEncoded()));
        System.out.println("Salt used (hex): " + bytesToHex(salt));
    }

    // Helper method to convert bytes to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}