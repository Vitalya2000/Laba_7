package com.example.laba7;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class EditActivity extends Activity implements View.OnClickListener {
    Button btn_save;
    EditText et_ID,etName2,etPrice2,etQuant2;
    MyDBHelper dbHelper;
    int del = new Random().nextInt((5 - 3) + 1) + 3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        btn_save = (Button) findViewById(R.id.button_ed);
        btn_save.setOnClickListener(this);

        et_ID = (EditText) findViewById(R.id.etID);
        etName2 = (EditText) findViewById(R.id.etName2);
        etPrice2 = (EditText) findViewById(R.id.etPrice2);
        etQuant2 = (EditText) findViewById(R.id.etQuant2);

        dbHelper = new MyDBHelper(this);
    }
    @Override
    public void onClick(View v) {
        new Thread(()->{
        String id2 = et_ID.getText().toString();
        String name2 = etName2.getText().toString();
        String price2 = etPrice2.getText().toString();
        String quant2 = etQuant2.getText().toString();


        SQLiteDatabase database4 = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

                contentValues.put(MyDBHelper.KEY_ID, id2);
                contentValues.put(MyDBHelper.KEY_NAME, name2);
                contentValues.put(MyDBHelper.KEY_PRICE, price2);
                contentValues.put(MyDBHelper.KEY_QUANTITY, quant2);
                database4.update(MyDBHelper.TABLE_GOODS, contentValues, MyDBHelper.KEY_ID + "=" + id2, null);
                dbHelper.close();
            try {
                Thread.sleep(del*1000);
                startActivity(new Intent(EditActivity.this,Back_End.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }).start();
    }
}
