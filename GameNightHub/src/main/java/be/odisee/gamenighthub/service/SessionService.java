package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.Game;
import be.odisee.gamenighthub.model.Session;
import be.odisee.gamenighthub.repository.GameRepository;
import be.odisee.gamenighthub.repository.SessionRepository;

import java.time.LocalDate;

public class SessionService {
    private SessionRepository sessionRepository;
    private GameRepository gamerRepository;

    public SessionService(SessionRepository sessionRepository, GameRepository gamerRepository) {
        this.sessionRepository = sessionRepository;
        this.gamerRepository = gamerRepository;
    }

    Session plan(String gameTitel, LocalDate datum, String locatie) {
        Game game = gamerRepository.zoekOpTitel(gameTitel);
        if (game == null)
            throw new IllegalArgumentException("Spel niet gevonden");
        Session session = new Session(game, datum, locatie);
        sessionRepository.save(session);
        return session;
    }

}
