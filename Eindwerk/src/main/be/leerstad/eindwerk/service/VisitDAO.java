package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Visit;

import java.util.List;
import java.util.Map;

public interface VisitDAO extends InteractionDAO<Visit> {

    void insertVisit(Visit visit);

    void insertVisits(List<Visit> visits);

    Map<String,Visit> fillCache();

}
