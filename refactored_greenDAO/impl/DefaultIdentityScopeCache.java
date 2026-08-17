package org.greenrobot.greendao.solid.impl;

import org.greenrobot.greendao.solid.api.IdentityScopeCache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Concrete in-memory identity scope cache implementation.
 * Encapsulates thread synchronization and cache storage cleanly (SRP).
 */
public class DefaultIdentityScopeCache<K, T> implements IdentityScopeCache<K, T> {
    private final Map<K, T> map = new HashMap<K, T>();
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public T get(K key) {
        lock.lock();
        try {
            return map.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T getNoLock(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, T entity) {
        lock.lock();
        try {
            map.put(key, entity);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void putNoLock(K key, T entity) {
        map.put(key, entity);
    }

    @Override
    public boolean detach(K key, T entity) {
        lock.lock();
        try {
            if (map.get(key) == entity) {
                map.remove(key);
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void remove(K key) {
        lock.lock();
        try {
            map.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            map.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void unlock() {
        lock.unlock();
    }
}
