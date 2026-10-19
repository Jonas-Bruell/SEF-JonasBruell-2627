package be.odisee.gamenighthub.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private final Game game;
    public Game getGame() {
        return game;
    }

    private LocalDate datum;
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    private String location;
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    private List<Player> spelers;
    public List<Player> getSpelers() {
        return spelers;
    }

    private List<Score> scores;
    public List<Score> getScores() {
        return scores;
    }

    public Session(Game game, LocalDate datum, String location) {
        this.game = game;
        this.datum = datum;
        this.location = location;
        this.spelers = new ArrayList<>();
        this.scores = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(game, session.game) && Objects.equals(datum, session.datum) && Objects.equals(location, session.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(game, datum, location);
    }

    public void voegSpelerToe(Player speler) {
        if (!spelers.contains(speler)) {
            spelers.add(speler);
        }
    }

    public void registreerUitslag(Player speler, int punten, boolean gewonnen) {
        if (spelers.contains(speler)) {
            scores.add(new Score(speler, punten, gewonnen));
        }
    }

    public boolean isVolzet() {
        return (spelers.size() > game.getMaxSpelers());
    }

}
