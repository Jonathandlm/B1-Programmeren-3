package main.be.leerstad.eindwerk.service;

import main.be.leerstad.eindwerk.model.Interaction;

public interface InteractionDAO {

    void saveInteraction(Interaction interaction);
    Interaction loadInteraction(Integer interactionId);

}
