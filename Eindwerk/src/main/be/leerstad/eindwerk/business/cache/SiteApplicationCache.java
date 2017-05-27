package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.SiteApplication;
import be.leerstad.eindwerk.service.SiteApplicationDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;

public class SiteApplicationCache implements ICache<Integer,String> {

    private static final Logger LOG = Logger.getLogger(SiteApplicationCache.class.getName());

    private Map<Integer,String> cache;

    private static SiteApplicationCache instance;

    private SiteApplicationCache() {
        cache = new HashMap<>();
        fill();
    }

    public synchronized static SiteApplicationCache getInstance() {
        if (instance == null) {
            instance = new SiteApplicationCache();
        }
        return instance;
    }

    public SiteApplication getSiteApplication(String siteApplication) {
        if (cache == null) {
            fill();
            LOG.log(Level.ERROR, "Singleton cache was null.");
        }
        if (containsValue(siteApplication)) {
            for (Map.Entry<Integer, String> entry : cache.entrySet()) {
                if (siteApplication.equals(entry.getValue())) {
                    return new SiteApplication(entry.getKey(), siteApplication);
                }
            }
        }
        int newSiteApplicationId = isEmpty() ? 1 : Collections.max(cache.keySet()) + 1;
        SiteApplication newSiteApplication = new SiteApplication(newSiteApplicationId, siteApplication);
        addSiteApplication(newSiteApplication);
        return newSiteApplication;
    }

    private void addSiteApplication(SiteApplication siteApplication) {
        put(siteApplication.getApplicationId(), siteApplication.getApplication());
        SiteApplicationDAOImpl.getInstance().insertSiteApplication(siteApplication);
        LOG.log(Level.DEBUG, "Added new " + siteApplication);
    }

    @Override
    public boolean containsKey(Integer integer) {
        return cache.containsKey(integer);
    }

    @Override
    public boolean containsValue(String siteApplication ) {
        return cache.containsValue(siteApplication );
    }

    @Override
    public String get(Integer key) {
        return cache.get(key);
    }

    @Override
    public Collection<String> values() {
        return cache.values();
    }

    @Override
    public void put(Integer integer, String siteApplication ) {
        cache.put(integer, siteApplication );
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
        cache = SiteApplicationDAOImpl.getInstance().fillCache();
    }

    @Override
    public Iterator<String> iterator() {
        return  cache.values().iterator();
    }

}
