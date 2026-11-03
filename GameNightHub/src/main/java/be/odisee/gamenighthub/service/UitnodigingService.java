package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.model.Session;

public class UitnodigingService {
    private Mailer mailer;

    public UitnodigingService(Mailer mailer) {
        this.mailer = mailer;
    }

    void stuurUitnodiging(Session session) {
        throw new UnsupportedOperationException("nog te implementeren");
    }

}
