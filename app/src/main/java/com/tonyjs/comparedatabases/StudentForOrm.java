package com.tonyjs.comparedatabases;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by tonyjs on 15. 7. 15..
 */
@DatabaseTable(tableName = "student_for_orm")
public class StudentForOrm {
    @DatabaseField
    private int number;
    @DatabaseField
    private String name;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

}
