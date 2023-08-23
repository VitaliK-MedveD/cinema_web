package by.it.medved.services.impl;

import by.it.medved.services.EncryptionService;
import by.it.medved.util.EncryptAlgorithmConstants;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    @Override
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        try {
            random = SecureRandom.getInstance(EncryptAlgorithmConstants.SHA1);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    public byte[] getEncryptedPassword(String password, byte[] salt) {
        int derivedKeyLength = 128;
        int iterations = 20000;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance(EncryptAlgorithmConstants.PBKDF2);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean authenticate(String enteredPassword, byte[] encryptedPassword, byte[] salt) {
        byte[] encryptedAttemptedPassword = getEncryptedPassword(enteredPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }
}