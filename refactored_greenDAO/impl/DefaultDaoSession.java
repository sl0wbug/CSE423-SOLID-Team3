package org.greenrobot.greendao.solid.impl;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.solid.api.DaoSession;
import org.greenrobot.greendao.solid.api.EntityRepository;
import org.greenrobot.greendao.solid.api.TransactionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete DaoSession implementation coordinating repositories and transaction management.
 * Follows SRP and DIP by delegating repository management and transaction execution to dedicated abstractions.
 */
public class DefaultDaoSession implements DaoSession {
    private final Database db;
    private final TransactionManager transactionManager;
    private final Map<Class<?>, EntityRepository<?, ?>> repositoryMap;

    public DefaultDaoSession(Database db, TransactionManager transactionManager) {
        this.db = db;
        this.transactionManager = transactionManager;
        this.repositoryMap = new HashMap<Class<?>, EntityRepository<?, ?>>();
    }

    public <T, K> void registerRepository(Class<T> entityClass, EntityRepository<T, K> repository) {
        repositoryMap.put(entityClass, repository);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T, K> EntityRepository<T, K> getRepository(Class<T> entityClass) {
        EntityRepository<?, ?> repository = repositoryMap.get(entityClass);
        if (repository == null) {
            throw new DaoException("No repository registered for " + entityClass);
        }
        return (EntityRepository<T, K>) repository;
    }

    @Override
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    @Override
    public Database getDatabase() {
        return db;
    }
}
