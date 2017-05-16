package be.leerstad.eindwerk.service;

import java.util.List;

public interface InteractionDAO<T> {

    List<T> getInteractions();

    T getInteraction(String id);

    void insertInteraction(T interaction);

    void deleteInteraction(T interaction);

    void updateInteraction(T interaction);

}
