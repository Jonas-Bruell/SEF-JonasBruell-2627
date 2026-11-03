package be.odisee.gamenighthub.service;

import be.odisee.gamenighthub.repository.SessionRepository;

import java.util.List;

public class LeaderboardService {
    private SessionRepository sessionRepository;
    private LeaderboardCalculator leaderboardCalculator;

    public LeaderboardService(SessionRepository sessionRepository, LeaderboardCalculator leaderboardCalculator) {
        this.sessionRepository = sessionRepository;
        this.leaderboardCalculator = leaderboardCalculator;
    }

    List<LeaderboardEntry> geefLeaderboard() {
        throw new UnsupportedOperationException("nog te implementeren");
    }

}
