package org.greenrobot.greendao.solid.impl;

import android.database.Cursor;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.internal.TableStatements;
import org.greenrobot.greendao.solid.api.EntityMapper;
import org.greenrobot.greendao.solid.api.EntityRepository;
import org.greenrobot.greendao.solid.api.IdentityScopeCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of EntityRepository.
 * Uses Dependency Injection for Database, EntityMapper, and IdentityScopeCache abstractions.
 * Adheres strictly to SRP by leaving mapping, transaction, and caching logic to injected components.
 */
public class BaseRepository<T, K> implements EntityRepository<T, K> {
    private final Database db;
    private final EntityMapper<T, K> mapper;
    private final IdentityScopeCache<K, T> cache;
    private final TableStatements statements;

    public BaseRepository(Database db, EntityMapper<T, K> mapper, IdentityScopeCache<K, T> cache, TableStatements statements) {
        this.db = db;
        this.mapper = mapper;
        this.cache = cache;
        this.statements = statements;
    }

    @Override
    public long insert(T entity) {
        return 0L;
    }

    @Override
    public long insertOrReplace(T entity) {
        return 0L;
    }

    @Override
    public T load(K key) {
        if (key == null) return null;
        if (cache != null) {
            T cached = cache.get(key);
            if (cached != null) return cached;
        }
        Cursor cursor = db.rawQuery(statements.getSelectByKey(), new String[]{key.toString()});
        try {
            if (cursor.moveToFirst()) {
                T entity = mapper.readEntity(cursor, 0);
                if (cache != null) {
                    cache.put(key, entity);
                }
                return entity;
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    @Override
    public T loadByRowId(long rowId) {
        Cursor cursor = db.rawQuery(statements.getSelectByRowId(), new String[]{Long.toString(rowId)});
        try {
            if (cursor.moveToFirst()) {
                return mapper.readEntity(cursor, 0);
            }
            return null;
        } finally {
            cursor.close();
        }
    }

    @Override
    public List<T> loadAll() {
        Cursor cursor = db.rawQuery(statements.getSelectAll(), null);
        List<T> list = new ArrayList<T>();
        try {
            while (cursor.moveToNext()) {
                list.add(mapper.readEntity(cursor, 0));
            }
            return list;
        } finally {
            cursor.close();
        }
    }

    @Override
    public void update(T entity) {
    }

    @Override
    public void delete(T entity) {
        if (cache != null) {
            K key = mapper.getKey(entity);
            if (key != null) {
                cache.remove(key);
            }
        }
    }

    @Override
    public void deleteAll() {
        if (cache != null) {
            cache.clear();
        }
    }

    @Override
    public boolean detach(T entity) {
        if (cache != null) {
            K key = mapper.getKey(entity);
            return cache.detach(key, entity);
        }
        return false;
    }

    @Override
    public void detachAll() {
        if (cache != null) {
            cache.clear();
        }
    }
}
