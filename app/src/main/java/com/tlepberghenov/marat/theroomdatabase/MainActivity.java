package com.tlepberghenov.marat.theroomdatabase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.tlepberghenov.marat.theroomdatabase.data.DbHelper;
import com.tlepberghenov.marat.theroomdatabase.data.TheRoomDataBaseContract;

public class MainActivity extends AppCompatActivity {

    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TemperatureActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new DbHelper(this);
    }

    @Override
    protected void onStart() {

        super.onStart();
        displayDatabaseInfo();
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

    @SuppressLint("SetTextI18n")
    private void displayDatabaseInfo() {
        //Create and open db
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        //Create list of columns
        String[] projection = {
                TheRoomDataBaseContract.TemperatureHumidity._ID,
                TheRoomDataBaseContract.TemperatureHumidity.COLUMN_TEMPERATURE,
                TheRoomDataBaseContract.TemperatureHumidity.COLUMN_HUMIDITY,
                TheRoomDataBaseContract.TemperatureHumidity.COLUM_ROOM
        };

        String selection = TheRoomDataBaseContract.TemperatureHumidity._ID  + " >?";
        String[] selectionsArgs = {"1"};

        //Does request
        Cursor cursor = db.query(
                TheRoomDataBaseContract.TemperatureHumidity.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);
        TextView displayTextView = (TextView) findViewById(R.id.text_view_info);

        try {
            displayTextView.setText("Table contains " + cursor.getCount() + " note/s.\n\n");

            displayTextView.append(TheRoomDataBaseContract.TemperatureHumidity._ID + " | " +
                    TheRoomDataBaseContract.TemperatureHumidity.COLUMN_TEMPERATURE + " | " +
                    TheRoomDataBaseContract.TemperatureHumidity.COLUMN_HUMIDITY + " | " +
                    TheRoomDataBaseContract.TemperatureHumidity.COLUM_ROOM + " | \n");

            //Finds index
            int idColumnIndex = cursor.getColumnIndex(TheRoomDataBaseContract.TemperatureHumidity._ID);
            int temperatureIndex = cursor.getColumnIndex(TheRoomDataBaseContract.TemperatureHumidity.COLUMN_TEMPERATURE);
            int humidityIndex = cursor.getColumnIndex(TheRoomDataBaseContract.TemperatureHumidity.COLUMN_HUMIDITY);
            int roomIndex = cursor.getColumnIndex(TheRoomDataBaseContract.TemperatureHumidity.COLUM_ROOM);

            //Going to all rows
            while (cursor.moveToNext()) {
                //Uses an index for getting line or number
                int currentId = cursor.getInt(idColumnIndex);
                int currentTemperature = cursor.getInt(temperatureIndex);
                int currentHumidity = cursor.getInt(humidityIndex);
                int currentRoom = cursor.getInt(roomIndex);

                //show all data from rows on the TextView
                displayTextView.append(("\n" + currentId + " | " + currentTemperature + " | " + currentHumidity + " | " + currentRoom));
            }
        } finally {
            cursor.close();
        }
    }
}
