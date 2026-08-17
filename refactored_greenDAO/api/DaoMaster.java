package org.greenrobot.greendao.solid.api;

import org.greenrobot.greendao.database.Database;

/**
 * Interface managing database sessions and schema versioning.
 * Follows Single Responsibility Principle (SRP) and Dependency Inversion Principle (DIP).
 */
public interface DaoMaster {
    int getSchemaVersion();
    Database getDatabase();
    DaoSession newSession();
}
