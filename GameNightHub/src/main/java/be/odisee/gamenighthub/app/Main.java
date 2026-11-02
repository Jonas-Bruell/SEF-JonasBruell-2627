package be.odisee.gamenighthub.app;

import be.odisee.gamenighthub.model.*;
import be.odisee.gamenighthub.service.LeaderboardCalculator;
import be.odisee.gamenighthub.service.LeaderboardEntry;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // gebruikers en spelers
        Player sara = new Player("Sara", new User("Sara", "sara@example.com", "ww"));
        Player emma = new Player("Emma", new User("Emma", "emma@example.com", "ww"));
        Player lien = new Player("Lien", new User("Lien", "lien@example.com", "ww"));

        // spellen
        Game catan = new Game("Catan", 3, 4, 90);
        Game uno = new Game("Uno", 2, 10, 20);
        Game cluedo = new Game("Cluedo", 3, 6, 120);
        List<Game> collectie = List.of(catan, uno, cluedo);

        // een game-avond
        Session avond = new Session(catan, LocalDate.of(2026, 10, 9), "Bij Sara thuis");
        avond.voegSpelerToe(sara);
        avond.voegSpelerToe(emma);
        avond.voegSpelerToe(lien);
        avond.registreerUitslag(sara, 10, true);
        avond.registreerUitslag(emma, 5, false);
        avond.registreerUitslag(lien, 7, false);
        Session avond2 = new Session(uno, LocalDate.of(2026, 10, 10), "Bij Emma thuis");
        avond2.voegSpelerToe(sara);
        avond2.voegSpelerToe(lien);
        avond2.voegSpelerToe(emma);
        avond2.registreerUitslag(sara, 12, false);
        avond2.registreerUitslag(lien, 15, true);
        avond2.registreerUitslag(emma, 7, false);
        Session avond3 = new Session(cluedo, LocalDate.of(2026, 10, 11), "Bij Lien thuis");
        avond3.voegSpelerToe(sara);
        avond3.voegSpelerToe(lien);
        avond3.voegSpelerToe(emma);
        avond3.registreerUitslag(sara, 14, true);
        avond3.registreerUitslag(lien, 13, false);
        avond3.registreerUitslag(emma, 7, false);
        List<Session> sessies = List.of(avond, avond2, avond3);

        //afdrukken
        System.out.println("Spellencollectie:");
        for (Game game : collectie) {
            System.out.println("  - " + game);
        }
        System.out.printf("Game-avond op %s (%s) - %s%n", avond.getDatum(), avond.getLocation(), avond.getGame().getTitel());
        System.out.println("Geschikt voor " + avond.getSpelers().size() + " spelers? " + catan.isGeschiktVoor(avond.getSpelers().size()));
        for (Score score : avond.getScores()) {
            System.out.println("  - " + score);
        }
        System.out.printf("Game-avond op %s (%s) - %s%n", avond2.getDatum(), avond2.getLocation(), avond2.getGame().getTitel());
        System.out.println("Geschikt voor " + avond2.getSpelers().size() + " spelers? " + uno.isGeschiktVoor(avond2.getSpelers().size()));
        for (Score score : avond2.getScores()) {
            System.out.println("  - " + score);
        }
        System.out.printf("Game-avond op %s (%s) - %s%n", avond3.getDatum(), avond3.getLocation(), avond3.getGame().getTitel());
        System.out.println("Geschikt voor " + avond3.getSpelers().size() + " spelers? " + cluedo.isGeschiktVoor(avond3.getSpelers().size()));
        for (Score score : avond3.getScores()) {
            System.out.println("  - " + score);
        }
        List<LeaderboardEntry> leaderboard = LeaderboardCalculator.berekenLeaderboard(sessies);
        System.out.println("Leaderboard:" + leaderboard.getFirst().getAantalOverwinningen());
        System.out.println("Leaderboard:" + leaderboard.getFirst().getSpeler());
        System.out.println("Leaderboard:" + leaderboard.getFirst().getTotaalPunten());
        System.out.println("Leaderboard:" + leaderboard.getFirst().toString());
    }
}
