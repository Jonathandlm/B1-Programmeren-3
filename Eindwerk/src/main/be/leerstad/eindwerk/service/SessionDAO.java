package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Session;

import java.util.List;

public interface SessionDAO extends InteractionDAO<Session> {

    void insertSession(Session interaction);

    void insertSessions(List<Session> interactions);

}
