package org.greenrobot.greendao.solid.impl;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.solid.api.DaoMaster;
import org.greenrobot.greendao.solid.api.DaoSession;
import org.greenrobot.greendao.solid.api.TransactionManager;

/**
 * Concrete implementation of DaoMaster.
 * Handles schema version tracking and creates configured sessions cleanly (SRP/DIP).
 */
public class DefaultDaoMaster implements DaoMaster {
    private final Database db;
    private final int schemaVersion;

    public DefaultDaoMaster(Database db, int schemaVersion) {
        this.db = db;
        this.schemaVersion = schemaVersion;
    }

    @Override
    public int getSchemaVersion() {
        return schemaVersion;
    }

    @Override
    public Database getDatabase() {
        return db;
    }

    @Override
    public DaoSession newSession() {
        TransactionManager txManager = new DatabaseTransactionManager(db);
        return new DefaultDaoSession(db, txManager);
    }
}
