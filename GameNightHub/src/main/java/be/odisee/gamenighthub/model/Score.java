package be.odisee.gamenighthub.model;

import java.util.Objects;

public class Score {
    private Player speler;
    public Player getSpeler() {
        return speler;
    }
    public void setSpeler(Player speler) {
        this.speler = speler;
    }

    private int punten;
    public int getPunten() {
        return punten;
    }
    public void setPunten(int punten) {
        this.punten = punten;
    }

    private boolean gewonnen;
    public boolean isGewonnen() {
        return gewonnen;
    }
    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }

    public Score(Player speler, int punten, boolean gewonnen) {
        this.speler = speler;
        this.punten = punten;
        this.gewonnen = gewonnen;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return punten == score.punten && gewonnen == score.gewonnen && Objects.equals(speler, score.speler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(punten, gewonnen, speler);
    }

    @Override
    public String toString() {
        return speler.getBijnaam() + ": " + punten + " punten" + (gewonnen ? " (winnaar)" : "");
    }

}
