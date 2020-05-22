package com.example.laba7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Mydb";
    public static final String TABLE_GOODS = "Goods";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_PRICE = "PRICE";
    public static final String KEY_QUANTITY = "QUANTITY";
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_GOODS + "(" + KEY_ID + " integer primary key," + KEY_NAME + " text," + KEY_PRICE + " float," + KEY_QUANTITY + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_GOODS);
        onCreate(db);
    }
}
