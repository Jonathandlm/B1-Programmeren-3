package be.leerstad.eindwerk.service;

import be.leerstad.eindwerk.model.Logfile;

import java.util.List;
import java.util.Map;

public interface LogfileDAO {

    List<Logfile> getLogfiles();

    void insertLogfile(Logfile logfile);

    void insertLogfiles(List<Logfile> logfiles);

    Map<String,Logfile> fillCache();
}
