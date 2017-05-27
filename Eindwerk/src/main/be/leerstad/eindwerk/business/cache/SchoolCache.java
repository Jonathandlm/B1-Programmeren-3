package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.School;
import be.leerstad.eindwerk.service.SchoolDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SchoolCache implements ICache<String,School> {

    private static final Logger LOG = Logger.getLogger(SchoolCache.class.getName());

    private Map<String,School> cache;

    private static SchoolCache instance;

    private SchoolCache() {
        cache = new HashMap<>();
        fill();
    }

    public synchronized static SchoolCache getInstance() {
        if (instance == null) {
            instance = new SchoolCache();
        }
        return instance;
    }

    public School getSchool(String ipAddress) {
        if (cache == null) {
            fill();
            LOG.log(Level.ERROR, "Singleton cache was null.");
        }
        if (containsKey(ipAddress))
            return get(ipAddress);
        addSchool(ipAddress);
        return new School(ipAddress);
    }

    private void addSchool(String ipAddress) {
        School school = new School(ipAddress);
        put(ipAddress, school);
        SchoolDAOImpl.getInstance().insertSchool(school);
        LOG.log(Level.DEBUG, "Added new " + school);
    }

    @Override
    public boolean containsKey(String ipAddress) {
        return cache.containsKey(ipAddress);
    }

    @Override
    public boolean containsValue(School school) {
        return cache.containsValue(school);
    }

    @Override
    public School get(String key) {
        return cache.get(key);
    }

    @Override
    public Collection<School> values() {
        return cache.values();
    }

    @Override
    public void put(String ipAddress, School school) {
        cache.put(ipAddress, school);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public void fill() {
        cache = SchoolDAOImpl.getInstance().fillCache();
    }

    @Override
    public Iterator<School> iterator() {
        return cache.values().iterator();
    }
}
