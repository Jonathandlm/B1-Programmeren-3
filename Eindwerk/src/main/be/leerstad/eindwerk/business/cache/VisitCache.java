package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Visit;
import be.leerstad.eindwerk.service.VisitDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VisitCache implements ICache<String,Visit> {

    private static final Logger LOG = Logger.getLogger(VisitCache.class.getName());

    private Map<String,Visit> cache;

    private static VisitCache instance;

    private VisitCache() {
        cache = new HashMap<>();
        fill();
    }

    public synchronized static VisitCache getInstance() {
        if (instance == null) {
            instance = new VisitCache();
        }
        return instance;
    }

    public Visit getVisit(String visitId) {
        if (cache == null) {
            fill();
            LOG.log(Level.ERROR, "Singleton cache was null.");
        }
        if (containsKey(visitId)) {
            return get(visitId);
        }
        LOG.log(Level.WARN, "Unable to find Visit with id " + visitId);
        return null;
    }

    @Override
    public boolean containsKey(String visitId) {
        return cache.containsKey(visitId);
    }

    @Override
    public boolean containsValue(Visit visit) {
        return cache.containsValue(visit);
    }

    @Override
    public Visit get(String key) {
        return cache.get(key);
    }

    @Override
    public Collection<Visit> values() {
        return cache.values();
    }

    @Override
    public void put(String visitId, Visit visit) {
        cache.put(visitId, visit);
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
        cache = VisitDAOImpl.getInstance().fillCache();
    }

    @Override
    public Iterator<Visit> iterator() {
        return cache.values().iterator();
    }
}
