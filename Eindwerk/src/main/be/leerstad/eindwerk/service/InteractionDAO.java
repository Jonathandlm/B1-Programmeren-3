package be.leerstad.eindwerk.service;

public interface InteractionDAO<T> {

    void saveInteraction(T interaction);
    T loadInteraction(Integer interactionId);

}
