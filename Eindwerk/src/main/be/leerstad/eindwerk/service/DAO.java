package be.leerstad.eindwerk.service;

import java.sql.Connection;

public interface DAO {

    Connection getConnection() throws DAOException;

}