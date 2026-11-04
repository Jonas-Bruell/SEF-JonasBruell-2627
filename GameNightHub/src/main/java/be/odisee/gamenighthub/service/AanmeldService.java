package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.User;
import be.odisee.gamenighthub.repository.UserRepository;

public class AanmeldService {
    private UserRepository userRepository;
    private PasswordHasher passwordHasher;

    public AanmeldService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    User registreer(String naam, String email, String wachtwoord) {
        if (userRepository.bestaatEmail(email))
            throw new IllegalArgumentException("Email is al in gebruik");
        if (wachtwoord == null || wachtwoord.length() < 8)
            throw new IllegalArgumentException("Wachtwoord mag niet null zijn");
        User user = new User(naam, email, passwordHasher.hash(wachtwoord));
        userRepository.save(user);
        return user;
    }

    User meldAan(String email, String wachtwoord) {
        if (!userRepository.bestaatEmail(email))
            return null;
        User user = userRepository.findByEmail(email);
        if (!passwordHasher.matches(wachtwoord, user.getWachtwoordHash()))
            return null;
        return user;
    }

}
