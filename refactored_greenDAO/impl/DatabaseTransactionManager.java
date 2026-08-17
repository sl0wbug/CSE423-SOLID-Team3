package org.greenrobot.greendao.solid.impl;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.solid.api.TransactionManager;

import java.util.concurrent.Callable;

/**
 * Concrete implementation of TransactionManager.
 * Manages database transaction lifecycle cleanly (SRP).
 */
public class DatabaseTransactionManager implements TransactionManager {
    private final Database db;

    public DatabaseTransactionManager(Database db) {
        this.db = db;
    }

    @Override
    public void runInTransaction(Runnable runnable) {
        db.beginTransaction();
        try {
            runnable.run();
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public <V> V callInTransaction(Callable<V> callable) {
        db.beginTransaction();
        try {
            V result = callable.call();
            db.setTransactionSuccessful();
            return result;
        } catch (Exception e) {
            throw new DaoException("Callable failed in transaction", e);
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public <V> V callInTransactionNoException(Callable<V> callable) {
        return callInTransaction(callable);
    }
}
