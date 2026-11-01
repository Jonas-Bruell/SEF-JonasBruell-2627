package be.odisee.gamenighthub.model;

import java.util.List;
import java.util.Objects;

public class Player {
    private String bijnaam;
    public String getBijnaam() {
        return bijnaam;
    }
    public void setBijnaam(String bijnaam) {
        this.bijnaam = bijnaam;
    }

    private final User user;
    public User getUser() {
        return user;
    }

    public Player(String bijnaam, User user) {
        this.bijnaam = bijnaam;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(user, player.user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user);
    }

    @Override
    public String toString() {
        return "Player{" +
                "bijnaam='" + bijnaam + '\'' +
                '}';
    }

    public int berekenTotaalScore() {
        return 0;
    }

    public List<Score> bekijkGeschiedenis() {
        return null;
    }

}
