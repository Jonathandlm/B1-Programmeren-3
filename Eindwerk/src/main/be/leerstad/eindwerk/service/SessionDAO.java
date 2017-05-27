package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Session;

import java.util.List;
import java.util.Map;

public interface SessionDAO extends InteractionDAO<Session> {

    void insertSession(Session interaction);

    void insertSessions(List<Session> interactions);

    Map<String,Session> fillCache();

}
