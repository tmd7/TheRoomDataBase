package com.tlepberghenov.marat.theroomdatabase;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tlepberghenov.marat.theroomdatabase.data.RoomsDbHelper;
import com.tlepberghenov.marat.theroomdatabase.data.TheRoomDataBaseContract;

public class RoomsActivity extends AppCompatActivity {
    private EditText mNameOfRoomEditText;
    private EditText mSizeOfRoomEditText;
    private EditText mDescriptionOfRoomEditText;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNameOfRoomEditText = (EditText) findViewById(R.id.name_of_room);
        mSizeOfRoomEditText = (EditText) findViewById(R.id.size_of_room);
        mDescriptionOfRoomEditText = (EditText) findViewById(R.id.description_of_room);
        mSaveButton = (Button) findViewById(R.id.save_button2);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertRoomInformation();
                mNameOfRoomEditText.setText("");
                mSizeOfRoomEditText.setText("");
                mDescriptionOfRoomEditText.setText("");
            }
        });
    }

    private void insertRoomInformation() {
        String NameOfRoomString = mNameOfRoomEditText.getText().toString().trim();
        String SizeOfRoomString = mSizeOfRoomEditText.getText().toString().trim();
        String DescriptionString = mDescriptionOfRoomEditText.getText().toString().trim();

        int sizeOfRoom = Integer.parseInt(SizeOfRoomString);

        RoomsDbHelper  mRoomsDbHelper = new RoomsDbHelper(this);

        SQLiteDatabase db = mRoomsDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TheRoomDataBaseContract.Rooms.COLUMN_NAME, NameOfRoomString);
        values.put(TheRoomDataBaseContract.Rooms.COLUMN_SIZE, sizeOfRoom);
        values.put(TheRoomDataBaseContract.Rooms.COLUMN_DESCRIPTION, DescriptionString);

        long newRowId = db.insert(TheRoomDataBaseContract.Rooms.TABLE_NAME, null, values);

        //Shows message about status inserting
        if (newRowId == -1) {
            //If -1 it's error
            Toast.makeText(this, "Error inserting dates", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Date  created", Toast.LENGTH_SHORT).show();
        }
    }

}
