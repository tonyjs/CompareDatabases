package com.tonyjs.comparedatabases;

import java.util.List;

/**
 * Created by tonyjs on 15. 7. 15..
 */
public interface Database {
    void insert(int number, String name);

    List<?> query();

    void onStop();
}
