package org.greenrobot.greendao.solid.impl;

import android.database.Cursor;
import org.greenrobot.greendao.solid.api.EntityMapper;
import org.greenrobot.greendao.solid.api.EntityRepository;
import org.greenrobot.greendao.solid.api.UnitTestDaoAccess;

/**
 * Concrete implementation of UnitTestDaoAccess.
 * Uses direct composition and abstraction injection instead of reflection hacks (SRP/DIP).
 */
public class DefaultUnitTestDaoAccess<T, K> implements UnitTestDaoAccess<T, K> {
    private final EntityRepository<T, K> repository;
    private final EntityMapper<T, K> mapper;

    public DefaultUnitTestDaoAccess(EntityRepository<T, K> repository, EntityMapper<T, K> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public K getKey(T entity) {
        return mapper.getKey(entity);
    }

    @Override
    public T readEntity(Cursor cursor, int offset) {
        return mapper.readEntity(cursor, offset);
    }

    @Override
    public K readKey(Cursor cursor, int offset) {
        return mapper.readKey(cursor, offset);
    }

    @Override
    public boolean isEntityUpdateable() {
        return mapper.isEntityUpdateable();
    }

    @Override
    public EntityRepository<T, K> getRepository() {
        return repository;
    }
}
