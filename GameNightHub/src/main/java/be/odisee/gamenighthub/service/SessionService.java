package be.odisee.gamenighthub.service;

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
        throw new UnsupportedOperationException("nog te implementeren");
    }

}
