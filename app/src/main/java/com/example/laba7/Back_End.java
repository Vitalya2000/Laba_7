package com.example.laba7;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Back_End extends AppCompatActivity implements View.OnClickListener {
    Button btn_watch, btn_save,btn_add;
    EditText etName,etPrice,etQuant;
    MyDBHelper dbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_end);

        btn_watch = (Button) findViewById(R.id.button_watch);
        btn_watch.setOnClickListener(this);

        btn_save = (Button) findViewById(R.id.button_edit);
        btn_save.setOnClickListener(this);

        btn_add = (Button) findViewById(R.id.button_add);
        btn_add.setOnClickListener(this);

        etName = (EditText) findViewById(R.id.etName);
        etPrice = (EditText) findViewById(R.id.etPrice);
        etQuant = (EditText) findViewById(R.id.etQuant);

        dbHelper = new MyDBHelper(this);
    }
    @Override
    public void onClick(View v) {

        String name = etName.getText().toString();
        String price = etPrice.getText().toString();
        String quant = etQuant.getText().toString();


        SQLiteDatabase database2 = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.button_watch:
                Intent intent = new Intent(this, ShowActivity.class);
                startActivity(intent);
                break;

            case R.id.button_edit:
                Intent intent2 = new Intent(this, EditActivity.class);
                startActivity(intent2);
                break;

            case R.id.button_add:
                contentValues.put(MyDBHelper.KEY_NAME, name);
                contentValues.put(MyDBHelper.KEY_PRICE, price);
                contentValues.put(MyDBHelper.KEY_QUANTITY, quant);
                database2.insert(MyDBHelper.TABLE_GOODS, null, contentValues);
                break;

        }

        dbHelper.close();
    }
}