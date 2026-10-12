package be.odisee.gamenighthub.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final UUID id;
    public UUID getId() {
        return id;
    }

    private String naam;
    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }

    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    private String wachtwoordHash;
    public String getWachtwoordHash() {
        return wachtwoordHash;
    }
    public void setWachtwoordHash(String wachtwoordHash) {
        this.wachtwoordHash = wachtwoordHash;
    }

    public User(UUID id, String naam, String email, String wachtwoordHash) {
        this.id = id;
        this.naam = naam;
        this.email = email;
        this.wachtwoordHash = wachtwoordHash;
    }

    public void registreer() {}

    public boolean meldAan(String wachtwoord) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email); // gebruik een 'business relation' (zoals email) en geen databaseveld (zoals uuid)
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
