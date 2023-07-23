package by.it.medved.services;

public interface EncryptionService {

    byte[] generateSalt();

    byte[] getEncryptedPassword(String password, byte[] salt);

    boolean authenticate(String enteredPassword, byte[] encryptedPassword, byte[] salt);
}
