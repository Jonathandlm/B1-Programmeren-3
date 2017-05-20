package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Visit;

import java.util.List;

public interface VisitDAO extends InteractionDAO<Visit> {

    void insertVisit(Visit visit);

    void insertVisits(List<Visit> visits);

}
