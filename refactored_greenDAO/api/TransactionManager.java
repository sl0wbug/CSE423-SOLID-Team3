package org.greenrobot.greendao.solid.api;

import java.util.concurrent.Callable;

/**
 * Interface responsible for executing operations within database transaction boundaries.
 * Follows SRP by decoupling transaction execution from entity persistence logic.
 */
public interface TransactionManager {
    void runInTransaction(Runnable runnable);
    <V> V callInTransaction(Callable<V> callable);
    <V> V callInTransactionNoException(Callable<V> callable);
}
