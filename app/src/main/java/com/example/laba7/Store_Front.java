package com.example.laba7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Store_Front extends AppCompatActivity {

    ViewPager2 pager;
    RecyclerView.Adapter pagerAdapter;
    MyDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_front);

        dbHelper = new MyDBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query("Goods", null, null, null, null, null, null);

        pager = findViewById(R.id.pager);
        pagerAdapter = new MyPagerAdapter(c, dbHelper);
        pager.setAdapter(pagerAdapter);
    }
}
