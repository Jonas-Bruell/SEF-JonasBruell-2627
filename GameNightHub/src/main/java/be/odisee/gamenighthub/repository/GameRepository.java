package be.odisee.gamenighthub.repository;

import be.odisee.gamenighthub.model.Game;

import java.util.List;

public interface GameRepository {
    void save(Game game);
    List<Game> findAll();
    Game findByTitle(String title);
    void delete(Game game);
}
