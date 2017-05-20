package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    User getUser(String userId);

    void insertUser(User user);

    void insertUsers(List<User> users);

}
