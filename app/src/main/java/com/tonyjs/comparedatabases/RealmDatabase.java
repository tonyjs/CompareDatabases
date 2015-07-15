package com.tonyjs.comparedatabases;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by tonyjs on 15. 7. 15..
 */
public class RealmDatabase implements Database {

    private Realm realm;

    public RealmDatabase(Context context) {
        realm = Realm.getInstance(context);
    }

    @Override
    public void insert(int number, String name) {
        realm.beginTransaction();
        StudentForRealm student = realm.createObject(StudentForRealm.class);
        student.setNumber(number);
        student.setName(name);
        realm.commitTransaction();
    }

    @Override
    public List<StudentForRealm> query() {
        RealmQuery<StudentForRealm> query = realm.where(StudentForRealm.class);
        return query.findAll();
    }

    @Override
    public void onStop() {
        realm.close();
    }
}
