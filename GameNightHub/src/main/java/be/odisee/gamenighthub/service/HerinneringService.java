package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.repository.SessionRepository;

import java.time.Clock;

public class HerinneringService {
    private Mailer mailer;
    private SessionRepository sessionRepository;
    private Clock clock;

    public HerinneringService(Mailer mailer, SessionRepository sessionRepository, Clock clock) {
        this.mailer = mailer;
        this.sessionRepository = sessionRepository;
        this.clock = clock;
    }

    int stuurHerinneringen() {
        throw new UnsupportedOperationException("nog te implementeren");
    }
}
