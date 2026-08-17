package org.greenrobot.greendao.solid.api;

/**
 * Interface for managing object identity scopes and in-memory caching.
 * Follows SRP by decoupling cache management from database operations.
 */
public interface IdentityScopeCache<K, T> {
    T get(K key);
    T getNoLock(K key);
    void put(K key, T entity);
    void putNoLock(K key, T entity);
    boolean detach(K key, T entity);
    void remove(K key);
    void clear();
    void lock();
    void unlock();
}
