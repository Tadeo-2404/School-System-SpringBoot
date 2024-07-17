package school.demo.config;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class Encryption {
    private static final int IV_LENGTH = 16;
    private static final String AES_CIPHER = "AES/CBC/PKCS5Padding";

    public static IvParameterSpec generateIv() {
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static String encrypt(String plainText, String secretKey) {
        try {
            SecretKey secretKeyBytes = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(AES_CIPHER);

            IvParameterSpec ivSpec = generateIv();
            cipher.init(Cipher.ENCRYPT_MODE, secretKeyBytes, ivSpec);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            String ivBase64 = Base64.getEncoder().encodeToString(ivSpec.getIV());
            String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);

            return shiftValues(ivBase64 + ":" + encryptedBase64);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed: " + e.getMessage(), e);
        }
    }

    public static String decrypt(String cipherText, String secretKey) {
        try {
            if (cipherText == null) {
                return null;
            }

            String token = reverseShiftValues(cipherText);

            if (token == null) {
                return null;
            }

            String[] parts = token.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid cipher text format");
            }

            byte[] ivBytes = Base64.getDecoder().decode(parts[0]);
            byte[] encryptedBytes = Base64.getDecoder().decode(parts[1]);

            SecretKey secretKeyBytes = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance(AES_CIPHER);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

            cipher.init(Cipher.DECRYPT_MODE, secretKeyBytes, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed: " + e.getMessage(), e);
        }
    }

    public static boolean isValidCipherText(String cipherText) {
        try {
            if (cipherText == null || cipherText.isEmpty()) {
                return false;
            }

            String[] parts = cipherText.split(":");
            if (parts.length != 2) {
                return false;
            }

            Base64.getDecoder().decode(parts[0]);
            Base64.getDecoder().decode(parts[1]);

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static String shiftValues(String cipherText) {
        if (cipherText == null || cipherText.isEmpty()) {
            return null;
        }

        String[] parts = cipherText.split(":");
        if (parts.length != 2) {
            return null;
        }

        String iv = parts[0];
        String token = parts[1];

        String newIvSplitFirst = iv.substring(0, iv.length()/2);
        String newIvSplitSecond = iv.substring(iv.length()/2, iv.length());

        String newTokenSplitFirst = token.substring(0, iv.length()/2);
        String newTokenSplitSecond = token.substring(iv.length()/2, iv.length());
        
        String shiftedTextIvF = shiftString(newIvSplitFirst, 1);
        String shiftedTextIvS = shiftString(newIvSplitSecond, 1);

        String shiftedTextTokenF = shiftString(newTokenSplitFirst, 1);
        String shiftedTextTokenS = shiftString(newTokenSplitSecond, 1);

        return shiftedTextIvS + shiftedTextTokenF + shiftedTextTokenS + shiftedTextIvF;
    }

    public static String reverseShiftValues(String shiftedText) {
        if (shiftedText == null || shiftedText.isEmpty()) {
            return null;
        }

        int totalLength = shiftedText.length();
        int halfLength = totalLength / 2;

        String shiftedTextIvS = shiftedText.substring(0, halfLength / 2);
        String shiftedTextTokenF = shiftedText.substring(halfLength / 2, halfLength);
        String shiftedTextTokenS = shiftedText.substring(halfLength, totalLength - halfLength / 2);
        String shiftedTextIvF = shiftedText.substring(totalLength - halfLength / 2);

        String newIvSplitFirst = shiftString(shiftedTextIvF, -1);
        String newIvSplitSecond = shiftString(shiftedTextIvS, -1);

        String newTokenSplitFirst = shiftString(shiftedTextTokenF, -1);
        String newTokenSplitSecond = shiftString(shiftedTextTokenS, -1);

        String iv = newIvSplitFirst + newIvSplitSecond;
        String token = newTokenSplitFirst + newTokenSplitSecond;

        return iv + ":" + token;
    }

    public static String shiftString(String text, int shift) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = shiftChar(chars[i], shift);
        }
        return new String(chars);
    }

    public static String reverseShiftString(String text, int shift) {
        return shiftString(text, -shift);
    }

    private static char shiftChar(char c, int shift) {
        // Ensure the shift wraps around the alphabet if necessary
        if (Character.isLetter(c)) {
            char base = Character.isLowerCase(c) ? 'a' : 'A';
            return (char) ((c - base + shift) % 26 + base);
        }
        return c; // Return the character unchanged if it is not a letter
    }
}
