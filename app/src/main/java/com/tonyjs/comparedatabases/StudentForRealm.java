package com.tonyjs.comparedatabases;

import io.realm.RealmObject;

/**
 * Created by tonyjs on 15. 7. 15..
 */
public class StudentForRealm extends RealmObject {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
