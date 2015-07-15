package com.tonyjs.comparedatabases;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {

                int max = 1000;

                SqlDatabase sqlDatabase = new SqlDatabase(getApplicationContext());
                OrmDatabase ormDatabase = new OrmDatabase(getApplicationContext());
                RealmDatabase realmDatabase = new RealmDatabase(getApplicationContext());

                Log.d("tony", "------------------ Insert start -----------------");

                long last = System.currentTimeMillis();
                Log.e("tony", "SqlDatabase >> start");
                for (int i = 1; i <= max; i++) {
                    sqlDatabase.insert(i, i + " tony " + i);
                }
                Log.e("tony", "SqlDatabase << end, time = " + (System.currentTimeMillis() - last));

                last = System.currentTimeMillis();
                Log.d("tony", "OrmDatabase >> start");
                for (int i = 1; i <= max; i++) {
                    ormDatabase.insert(i, i + " tony " + i);
                }
                Log.d("tony", "OrmDatabase << end, time = " + (System.currentTimeMillis() - last));

                last = System.currentTimeMillis();
                Log.i("tony", "RealmDatabase >> start");
                for (int i = 1; i <= max; i++) {
                    realmDatabase.insert(i, i + " tony " + i);
                }
                Log.i("tony", "RealmDatabase << end, time = " + (System.currentTimeMillis() - last));

                Log.d("tony", "------------------ Insert end -----------------");

                Log.w("tony", "------------------ Query start -----------------");

                last = System.currentTimeMillis();
                Log.e("tony", "SqlDatabase >> start");
                List<Student> students = sqlDatabase.query();
                for (Student student : students) {
                    Log.e("query", MainActivity.toString(student.getNumber(), student.getName()));
                }
                Log.e("tony", "SqlDatabase << end, time = " + (System.currentTimeMillis() - last));

                last = System.currentTimeMillis();
                Log.d("tony", "OrmDatabase >> start");
                List<StudentForOrm> studentForOrms = ormDatabase.query();
                for (StudentForOrm student : studentForOrms) {
                    Log.d("query", MainActivity.toString(student.getNumber(), student.getName()));
                }
                Log.d("tony", "OrmDatabase << end, time = " + (System.currentTimeMillis() - last));

                last = System.currentTimeMillis();
                Log.i("tony", "RealmDatabase >> start");
                List<StudentForRealm> studentForRealms = realmDatabase.query();
                for (StudentForRealm student : studentForRealms) {
                    Log.i("query", MainActivity.toString(student.getNumber(), student.getName()));
                }
                Log.i("tony", "RealmDatabase << end, time = " + (System.currentTimeMillis() - last));

                Log.w("tony", "------------------ Query end -----------------");
            }
        }).start();
    }

    public static String toString(int number, String name) {
        return "Student(" + number + ", " + name + ")";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
