package org.greenrobot.greendao.solid.api;

import android.database.Cursor;
import org.greenrobot.greendao.database.DatabaseStatement;

/**
 * Interface responsible for mapping entities to database rows and statement parameters.
 * Follows Single Responsibility Principle (SRP) by isolating object-relational mapping logic.
 */
public interface EntityMapper<T, K> {
    T readEntity(Cursor cursor, int offset);
    K readKey(Cursor cursor, int offset);
    void bindValues(DatabaseStatement stmt, T entity);
    K getKey(T entity);
    boolean isEntityUpdateable();
}
