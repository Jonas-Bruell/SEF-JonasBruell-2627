package be.odisee.gamenighthub.model;

import java.util.Objects;

public class Game {
    private String titel;
    public String getTitel() {
        return titel;
    }
    public void setTitel(String titel) {
        this.titel = titel;
    }

    private int minSpelers;
    public int getMinSpelers() {
        return minSpelers;
    }
    public void setMinSpelers(int minSpelers) {
        this.minSpelers = minSpelers;
    }

    private int maxSpelers;
    public int getMaxSpelers() {
        return maxSpelers;
    }
    public void setMaxSpelers(int maxSpelers) {
        this.maxSpelers = maxSpelers;
    }

    private int gemiddeldeSpeelduur;
    public int getGemiddeldeSpeelduur() {
        return gemiddeldeSpeelduur;
    }
    public void setGemiddeldeSpeelduur(int gemiddeldeSpeelduur) {
        this.gemiddeldeSpeelduur = gemiddeldeSpeelduur;
    }

    public Game(String titel, int minSpelers, int maxSpelers, int gemiddeldeSpeelduur) {
        this.titel = titel;
        this.minSpelers = minSpelers;
        this.maxSpelers = maxSpelers;
        this.gemiddeldeSpeelduur = gemiddeldeSpeelduur;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(titel, game.titel);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titel);
    }

    @Override
    public String toString() {
        return titel + '(' + minSpelers + "-" + maxSpelers + " spelers)";
    }

    public boolean isGeschiktVoor(int aantalSpelers) {
        return aantalSpelers >= minSpelers && aantalSpelers <= maxSpelers;
    }

}
