package com.example.laba7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_front,btn_back;
    MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_front = (Button) findViewById(R.id.button_front);
        btn_front.setOnClickListener(this);

        btn_back = (Button) findViewById(R.id.button_back);
        btn_back.setOnClickListener(this);

        dbHelper = new MyDBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        database.delete(MyDBHelper.TABLE_GOODS, null, null);

        contentValues.put(MyDBHelper.KEY_NAME, "ТОРТЫ");
        contentValues.put(MyDBHelper.KEY_PRICE, 550.22);
        contentValues.put(MyDBHelper.KEY_QUANTITY, 35);
        database.insert(MyDBHelper.TABLE_GOODS, null, contentValues);

        contentValues.put(MyDBHelper.KEY_NAME, "АВТОМОБИЛИ");
        contentValues.put(MyDBHelper.KEY_PRICE, 1532423.22);
        contentValues.put(MyDBHelper.KEY_QUANTITY, 50);
        database.insert(MyDBHelper.TABLE_GOODS, null, contentValues);

        contentValues.put(MyDBHelper.KEY_NAME, "ВЕЛОСИПЕДЫ");
        contentValues.put(MyDBHelper.KEY_PRICE, 8054.22);
        contentValues.put(MyDBHelper.KEY_QUANTITY, 75);
        database.insert(MyDBHelper.TABLE_GOODS, null, contentValues);

        dbHelper.close();
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.button_front:
                Intent intent = new Intent(this, Store_Front.class);
                startActivity(intent);
                break;

            case R.id.button_back:
                Intent intent2 = new Intent(this, Back_End.class);
                startActivity(intent2);
                break;

        }


    }


}

