package org.greenrobot.greendao.solid.impl;

import android.database.Cursor;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.solid.api.EntityMapper;

/**
 * Abstract default implementation of EntityMapper.
 * Implements SRP by isolating field binding and cursor conversion from DAO persistence operations.
 */
public abstract class DefaultEntityMapper<T, K> implements EntityMapper<T, K> {
    @Override
    public abstract T readEntity(Cursor cursor, int offset);

    @Override
    public abstract K readKey(Cursor cursor, int offset);

    @Override
    public abstract void bindValues(DatabaseStatement stmt, T entity);

    @Override
    public abstract K getKey(T entity);

    @Override
    public boolean isEntityUpdateable() {
        return true;
    }
}
