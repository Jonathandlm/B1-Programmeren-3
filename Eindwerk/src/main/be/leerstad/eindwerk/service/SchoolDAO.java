package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.School;

import java.util.List;
import java.util.Map;

public interface SchoolDAO {

    List<School> getSchools();

    School getSchool(String id);

    void insertSchool(School school);

    void deleteSchool(String ipAddress);

    Map<String,School> fillCache();
}
