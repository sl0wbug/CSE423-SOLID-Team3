package org.greenrobot.greendao.solid.api;

import java.util.List;

/**
 * Interface defining entity persistence operations.
 * Follows Interface Segregation Principle (ISP) by isolating CRUD operations from caching,
 * transaction, and SQL statement building concerns.
 */
public interface EntityRepository<T, K> {
    long insert(T entity);
    long insertOrReplace(T entity);
    T load(K key);
    T loadByRowId(long rowId);
    List<T> loadAll();
    void update(T entity);
    void delete(T entity);
    void deleteAll();
    boolean detach(T entity);
    void detachAll();
}
