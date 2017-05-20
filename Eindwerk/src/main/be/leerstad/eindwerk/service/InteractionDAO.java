package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;

import java.util.List;

public interface InteractionDAO<T> {

    List<T> getInteractions();

    void deleteInteraction(Logfile logfile);

    void deleteInteractions(List<Logfile> logfiles);

}
