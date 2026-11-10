package util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncryption {

    private static final int ITTERATIONS = 1_088_000;
    private static final int KEY_LENGTH = 512;


    public static byte[] saltGenerator(){
        byte[] salt = new byte[20];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    public static byte[] hash(char[] input, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(input, salt, ITTERATIONS, KEY_LENGTH);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return skf.generateSecret(spec).getEncoded(); //algoritme
        } finally {
            spec.clearPassword();   // sletter PBEKeySpec's kopi
        }
    }


    //Opret password (create user)
    public static String createStoredKey(char[] input) throws Exception{
        byte[] salt = saltGenerator();
        byte[] hash = hash(input, salt);
        return Base64.getEncoder().encodeToString(salt)+ ":" + Base64.getEncoder().encodeToString(hash);
    }


    //Sammenlign password (login)
    public static boolean checkPassword (char[] input, String stored) throws Exception {
        String[] parts = stored.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] storedHash = Base64.getDecoder().decode(parts[1]);
        byte[] generatedHash = hash(input,salt);

        return MessageDigest.isEqual(storedHash,generatedHash);
    }

    //Den metode som kalder checkPassword eller CreateStoredKey skal køre en finally efter try-catch:

    /* finally {
             Arrays.fill(pw, '\0');   // køres altid, også ved fejl
         } */




}
