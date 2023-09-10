package by.it.medved.services.impl;

import by.it.medved.config.EncryptionConfiguration;
import by.it.medved.exceptions.EncryptionException;
import by.it.medved.services.EncryptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class EncryptionServiceImpl implements EncryptionService {

    private final EncryptionConfiguration encryptionConfiguration;

    @Override
    @Transactional(readOnly = true)
    public byte[] generateSalt() {
        SecureRandom random;
        try {
            random = SecureRandom.getInstance(encryptionConfiguration.getCryptographicHashingAlgorithm());
        } catch (NoSuchAlgorithmException exception) {
            throw new EncryptionException(exception.getMessage());
        }
        byte[] salt = new byte[encryptionConfiguration.getSaltLength()];
        random.nextBytes(salt);
        return salt;
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getEncryptedPassword(String password, byte[] salt) {
        int derivedKeyLength = encryptionConfiguration.getDerivedKeyLength();
        int iterations = encryptionConfiguration.getIterations();
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory factory;
        try {
            factory = SecretKeyFactory.getInstance(encryptionConfiguration.getKeyGenerationAlgorithm());
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new EncryptionException(exception.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean authenticate(String enteredPassword, byte[] encryptedPassword, byte[] salt) {
        byte[] encryptedAttemptedPassword = getEncryptedPassword(enteredPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }
}