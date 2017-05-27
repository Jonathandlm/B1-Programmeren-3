package be.leerstad.eindwerk.business.cache;

import java.util.Collection;

public interface ICache<K, V> extends Iterable<V> {

    boolean containsKey(K k);

    boolean containsValue(V v);

    V get(K key);

    Collection<V> values();

    void put(K k, V v);

    int size();

    boolean isEmpty();

    void fill();

}
