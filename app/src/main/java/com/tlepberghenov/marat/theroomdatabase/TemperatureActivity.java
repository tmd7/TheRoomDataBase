package com.tlepberghenov.marat.theroomdatabase;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tlepberghenov.marat.theroomdatabase.data.DbHelper;
import com.tlepberghenov.marat.theroomdatabase.data.TheRoomDataBaseContract;

public class TemperatureActivity extends AppCompatActivity {
    private EditText mTemperatureEditText;
    private EditText mHumidityEditText;
    private Spinner mRoomSpinner;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        mTemperatureEditText = (EditText) findViewById(R.id.temperature);
        mHumidityEditText = (EditText) findViewById(R.id.humidity);
        mRoomSpinner = (Spinner) findViewById(R.id.room);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertTemperatureHumidity();
                mTemperatureEditText.setText("");
                mHumidityEditText.setText("");
            }
        });

        setupSpinner();
    }

    private void setupSpinner() {
        //Spinner setup
    }

    private void insertTemperatureHumidity() {
        String temperatureString = mTemperatureEditText.getText().toString().trim();
        String humidityString = mHumidityEditText.getText().toString().trim();

        int temperature = Integer.parseInt(temperatureString);
        int humidity = Integer.parseInt(humidityString);

        DbHelper mDbHelper = new DbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //Creates object ContentValues where names of rows is keys and information is value of keys
        ContentValues values = new ContentValues();
        values.put(TheRoomDataBaseContract.TemperatureHumidity.COLUMN_TEMPERATURE, temperature);
        values.put(TheRoomDataBaseContract.TemperatureHumidity.COLUMN_HUMIDITY, humidity);
        values.put(TheRoomDataBaseContract.TemperatureHumidity.COLUM_ROOM, 0);

        //Inserts a new line and remembers the his index
        long newRowId = db.insert(TheRoomDataBaseContract.TemperatureHumidity.TABLE_NAME, null, values);

        //Shows message about status inserting
        if (newRowId == -1) {
            //If -1 it's error
            Toast.makeText(this, "Error inserting dates", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Date  created", Toast.LENGTH_SHORT).show();
        }


    }
}
