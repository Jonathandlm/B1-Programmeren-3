package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.Site;
import be.leerstad.eindwerk.service.SiteDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.*;

public class SiteCache implements ICache<Integer,String> {

    private static final Logger LOG = Logger.getLogger(SiteCache.class.getName());

    private Map<Integer,String> cache;

    private static SiteCache instance;

    private SiteCache() {
        cache = new HashMap<>();
        fill();
    }

    public synchronized static SiteCache getInstance() {
        if (instance == null) {
            instance = new SiteCache();
        }
        return instance;
    }

    public Site getSite(String site) {
        if (cache == null) {
            fill();
            LOG.log(Level.ERROR, "Singleton cache was null.");
        }
        if (containsValue(site)) {
            for (Map.Entry<Integer, String> entry : cache.entrySet()) {
                if (site.equals(entry.getValue())) {
                    return new Site(entry.getKey(), site);
                }
            }
        }
        int newSiteId = isEmpty() ? 1 : Collections.max(cache.keySet()) + 1;
        Site newSite = new Site(newSiteId, site);
        addSite(newSite);
        return newSite;
    }

    private void addSite(Site site) {
        put(site.getSiteId(), site.getSite());
        SiteDAOImpl.getInstance().insertSite(site);
        LOG.log(Level.DEBUG, "Added new " + site);
    }

    @Override
    public boolean containsKey(Integer integer) {
        return cache.containsKey(integer);
    }

    @Override
    public boolean containsValue(String site) {
        return cache.containsValue(site);
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
    public void put(Integer integer, String site) {
        cache.put(integer, site);
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
        cache = SiteDAOImpl.getInstance().fillCache();
    }

    @Override
    public Iterator<String> iterator() {
        return cache.values().iterator();
    }
}
