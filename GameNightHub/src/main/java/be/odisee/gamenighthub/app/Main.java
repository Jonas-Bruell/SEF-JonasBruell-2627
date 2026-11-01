package be.odisee.gamenighthub.app;

import be.odisee.gamenighthub.model.*;

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
        List<Game> collectie = List.of(catan, uno);

        // een game-avond
        Session avond = new Session(catan, LocalDate.of(2026, 10, 9), "Bij Sara thuis");
        avond.voegSpelerToe(sara);
        avond.voegSpelerToe(emma);
        avond.voegSpelerToe(lien);
        avond.registreerUitslag(sara, 10, true);
        avond.registreerUitslag(emma, 5, false);
        avond.registreerUitslag(lien, 7, false);

        //afdrukken
        System.out.println("Spellencollectie:");
        for (Game game : collectie) {
            System.out.println("  - " + game);
        }
        System.out.printf("Game-avond op %s (%s) - %S%n", avond.getDatum(), avond.getLocation(), avond.getGame().getTitel());
        System.out.println("Geschikt voor " + avond.getSpelers().size() + " spelers? " + catan.isGeschiktVoor(avond.getSpelers().size()));
        for (Score score : avond.getScores()) {
            System.out.println("  - " + score);
        }
    }
}
