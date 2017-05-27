package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Session;
import be.leerstad.eindwerk.service.SessionDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SessionCache implements ICache<String,Session> {

    private static final Logger LOG = Logger.getLogger(SessionCache.class.getName());

    private Map<String,Session> cache;

    private static SessionCache instance;

    private SessionCache() {
        cache = new HashMap<>();
        fill();
    }

    public synchronized static SessionCache getInstance() {
        if (instance == null) {
            instance = new SessionCache();
        }
        return instance;
    }

    public Session getSession(String sessionId) {
        if (cache == null) {
            fill();
            LOG.log(Level.ERROR, "Singleton cache was null.");
        }
        if (containsKey(sessionId)) {
            return get(sessionId);
        }
        LOG.log(Level.WARN, "Unable to find Session with id " + sessionId);
        return null;
    }

    @Override
    public boolean containsKey(String sessionId) {
        return cache.containsKey(sessionId);
    }

    @Override
    public boolean containsValue(Session session) {
        return cache.containsValue(session);
    }

    @Override
    public Session get(String key) {
        return cache.get(key);
    }

    @Override
    public Collection<Session> values() {
        return cache.values();
    }

    @Override
    public void put(String sessionId, Session session) {
        cache.put(sessionId, session);
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
        cache = SessionDAOImpl.getInstance().fillCache();
    }

    @Override
    public Iterator<Session> iterator() {
        return cache.values().iterator();
    }
}
