package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.School;

import java.util.List;

public interface SchoolDAO {

    List<School> getSchools();

    School getSchool(String id);

    void insertSchool(School school);

    void insertSchools(List<School> schools);

}
