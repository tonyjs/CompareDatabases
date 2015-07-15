package com.tonyjs.comparedatabases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonyjs on 15. 7. 15..
 */
public class SqlDatabase implements Database {
    private DatabaseHelper databaseHelper;

    public SqlDatabase(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void insert(int number, String name) {
        databaseHelper.insert(number, name);
    }

    @Override
    public List<Student> query() {
        return databaseHelper.query();
    }

    @Override
    public void onStop() {
        databaseHelper.close();
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        interface TableStudent {
            String NAME = "student";
            String COLUMN_NUMBER = "number";
            String COLUMN_NAME = "name";
        }

        public static final String DATABASE_NAME = "class";
        public static final int DATABASE_VERSION = 1;

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TableStudent.NAME + " ("
                        + TableStudent.COLUMN_NUMBER + " INTEGER, "
                        + TableStudent.COLUMN_NAME + " TEXT);";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void insert(int number, String name) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues cv = new ContentValues();
            cv.put(TableStudent.COLUMN_NUMBER, number);
            cv.put(TableStudent.COLUMN_NAME, name);

            db.insert(TableStudent.NAME, null, cv);
        }

        public ArrayList<Student> query() {
            ArrayList<Student> list = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.query(
                    TableStudent.NAME, new String[]{TableStudent.COLUMN_NUMBER, TableStudent.COLUMN_NAME},
                    null, null, null, null, null);
            while (cursor.moveToNext()) {
                int number = cursor.getInt(cursor.getColumnIndex(TableStudent.COLUMN_NUMBER));
                String name = cursor.getString(cursor.getColumnIndex(TableStudent.COLUMN_NAME));
                Student student = new Student(number, name);
                list.add(student);
            }
            cursor.close();
            return list;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TableStudent.NAME);
            db.execSQL(SQL_CREATE_TABLE);
        }
    }

}
