package org.greenrobot.greendao.solid.api;

import org.greenrobot.greendao.database.Database;

/**
 * Interface representing a database session.
 * Exposes repository access and transaction management abstractions without exposing internal statement details.
 * Follows Dependency Inversion Principle (DIP) and Interface Segregation Principle (ISP).
 */
public interface DaoSession {
    <T, K> EntityRepository<T, K> getRepository(Class<T> entityClass);
    TransactionManager getTransactionManager();
    Database getDatabase();
}
