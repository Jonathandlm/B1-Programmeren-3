package be.leerstad.eindwerk.business.cache;

import be.leerstad.eindwerk.model.User;
import be.leerstad.eindwerk.service.UserDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserCache implements ICache<String,User> {

    private static final Logger LOG = Logger.getLogger(UserCache.class.getName());

    private Map<String,User> cache;

    private static UserCache instance;

    private UserCache() {
        cache = new HashMap<>();
        fill();
    }

    public synchronized static UserCache getInstance() {
        if (instance == null) {
            instance = new UserCache();
        }
        return instance;
    }

    public User getUser(String userId) {
        if (cache == null) {
            fill();
            LOG.log(Level.ERROR, "Singleton cache was null.");
        }
        if (containsKey(userId)) {
            return get(userId);
        }
        addUser(userId);
        return new User(userId);
    }

    private void addUser(String userId) {
        User user = new User(userId);
        put(userId, user);
        UserDAOImpl.getInstance().insertUser(user);
        LOG.log(Level.DEBUG, "Added new " + user);
    }

    @Override
    public boolean containsKey(String userId) {
        return cache.containsKey(userId);
    }

    @Override
    public boolean containsValue(User user) {
        return cache.containsValue(user);
    }

    @Override
    public User get(String key) {
        return cache.get(key);
    }

    @Override
    public Collection<User> values() {
        return cache.values();
    }

    @Override
    public void put(String userId, User user) {
        cache.put(userId, user);
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
        cache = UserDAOImpl.getInstance().fillCache();
    }

    @Override
    public Iterator<User> iterator() {
        return cache.values().iterator();
    }
}
