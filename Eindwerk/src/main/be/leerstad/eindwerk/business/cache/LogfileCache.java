package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Logfile;
import be.leerstad.eindwerk.service.LogfileDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;

public class LogfileCache implements ICache<String,Logfile> {

    private static final Logger LOG = Logger.getLogger(LogfileCache.class.getName());

    private Map<String,Logfile> cache;

    private static LogfileCache instance;

    private LogfileCache() {
        cache = new HashMap<>();
        fill();
    }

    public synchronized static LogfileCache getInstance() {
        if (instance == null) {
            instance = new LogfileCache();
        }
        return instance;
    }

    public Logfile getLogfile(String filename) {
        if (cache == null) {
            cache = new HashMap<>();
            fill();
            LOG.log(Level.ERROR, "Singleton cache was null.");
        }
        if (containsKey(filename))
            return get(filename);
        addLogfile(filename);
        return new Logfile(filename);
    }

    private void addLogfile(String filename) {
        Logfile logfile = new Logfile(filename);
        put(filename, logfile);
        LogfileDAOImpl.getInstance().insertLogfile(logfile);
        LOG.log(Level.DEBUG, "Added new " + logfile);
    }

    @Override
    public boolean containsKey(String logfileName) {
        return cache.containsKey(logfileName);
    }

    @Override
    public boolean containsValue(Logfile logfile) {
        return cache.containsValue(logfile);
    }

    @Override
    public Logfile get(String key) {
        return cache.get(key);
    }

    @Override
    public Collection<Logfile> values() {
        return cache.values();
    }

    @Override
    public void put(String logfileName, Logfile logfile) {
        cache.put(logfileName, logfile);
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
        cache = LogfileDAOImpl.getInstance().fillCache();
    }

    @Override
    public Iterator<Logfile> iterator() {
        return cache.values().iterator();
    }
}
