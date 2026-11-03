package be.odisee.gamenighthub.service;

public interface PasswordHasher {
    String hash(String password);
    boolean matches(String wachtwoord, String hash);
}
