package com.nikhil_suwalka.encryptiondecryptionusingaes;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public  class Encryption_and_Decryption_Functions {

    final static String ALGORITHM = "AES";

    public static String decrypt(byte[] cipherText, SecretKey key) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedText = cipher.doFinal(cipherText);
            return new String(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(byte[] plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] cipherText = cipher.doFinal(plaintext);
        return cipherText;
    }


    public static String encoderfun(byte[] decval) {
        String conVal = Base64.encodeToString(decval, Base64.DEFAULT);
        return conVal;
    }

    public static byte[] decoderfun(String encodedval) {
        return Base64.decode(encodedval, Base64.DEFAULT);
    }

    public static SecretKey getOriginalSecretKeyFromString(String strSecretKey){
        return new SecretKeySpec(Encryption_and_Decryption_Functions.decoderfun(strSecretKey), 0, Encryption_and_Decryption_Functions.decoderfun(strSecretKey).length, ALGORITHM);
    }

}
