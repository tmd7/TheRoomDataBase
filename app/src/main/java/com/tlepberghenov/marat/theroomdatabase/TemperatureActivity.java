package com.tlepberghenov.marat.theroomdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class TemperatureActivity extends AppCompatActivity {
    private EditText mTemperatureEditText;
    private EditText mHumidityEditText;
    private Spinner mRoomSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        mTemperatureEditText = (EditText) findViewById(R.id.temperature);
        mHumidityEditText = (EditText) findViewById(R.id.humidity);
        mRoomSpinner = (Spinner) findViewById(R.id.room);

        setupSpinner();
    }

    private void setupSpinner() {
        //Spinner setup
    }
}
