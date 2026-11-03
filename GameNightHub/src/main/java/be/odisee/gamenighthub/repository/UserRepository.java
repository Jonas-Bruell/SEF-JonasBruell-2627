package be.odisee.gamenighthub.repository;

import be.odisee.gamenighthub.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    List<User> findAll();
    User findByEmail(String email);
    void delete(User user);

    boolean bestaatEmail(String email);
    User zoekOpEmail(String email); // null als onbekend
}
