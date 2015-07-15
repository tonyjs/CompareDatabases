package com.tonyjs.comparedatabases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by tonyjs on 15. 7. 15..
 */
public class OrmDatabase implements Database {

    private Dao<StudentForOrm, Long> dao;
    public OrmDatabase(Context context) {
        ConnectionSource connectionSource = new AndroidConnectionSource(new DatabaseHelper(context));
        try {
            dao = DaoManager.createDao(connectionSource, StudentForOrm.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(int number, String name) {
        if (dao == null) {
            return;
        }
        StudentForOrm student = new StudentForOrm();
        student.setNumber(number);
        student.setName(name);
        try {
            dao.create(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StudentForOrm> query() {
        if (dao == null) {
            return Collections.emptyList();
        }
        List<StudentForOrm> students = null;
        try {
             students = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            students = Collections.emptyList();
        }
        return students;
    }

    @Override
    public void onStop() {
        if (dao == null) {
            return;
        }
        try {
            dao.getConnectionSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class DatabaseHelper extends OrmLiteSqliteOpenHelper {
        public static final String DATABASE_NAME = "class_for_orm";
        public static final int DATABASE_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
            try {
                TableUtils.createTableIfNotExists(connectionSource, StudentForOrm.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
            try {
                TableUtils.dropTable(connectionSource, StudentForOrm.class, true);
                onCreate(database, connectionSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
