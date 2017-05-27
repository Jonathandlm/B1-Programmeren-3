package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {

    List<User> getUsers();

    User getUser(String userId);

    void insertUser(User user);

    void deleteUser(String userId);

    Map<String,User> fillCache();
}
