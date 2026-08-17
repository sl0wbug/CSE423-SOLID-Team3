package org.greenrobot.greendao.solid.api;

import android.database.Cursor;

/**
 * Interface facilitating unit test verification of entity mapping and key extraction
 * without relying on unsafe reflection hacks.
 */
public interface UnitTestDaoAccess<T, K> {
    K getKey(T entity);
    T readEntity(Cursor cursor, int offset);
    K readKey(Cursor cursor, int offset);
    boolean isEntityUpdateable();
    EntityRepository<T, K> getRepository();
}
