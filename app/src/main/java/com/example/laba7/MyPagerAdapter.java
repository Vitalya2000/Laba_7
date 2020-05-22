package com.example.laba7;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class MyPagerAdapter extends CursorRecyclerAdapter<PagerVH> {
    MyDBHelper dbHelper;
    int p;
    int del = new Random().nextInt((5 - 3) + 1) + 3;

    //int k;
    public MyPagerAdapter(Cursor c, MyDBHelper dbHelper) {
        super(c);
        this.dbHelper = dbHelper;
    }

    public PagerVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page, parent, false);
        return new PagerVH(itemView);
    }

    public void onBindViewHolder(PagerVH holder, Cursor c) {

        final int qIndex = c.getColumnIndex(MyDBHelper.KEY_QUANTITY);
        holder.textView.setText("НАИМЕНОВАНИЕ: " + c.getString(1));
        holder.textView2.setText("СТОИМОСТЬ: " + c.getFloat(2));
        holder.textView3.setText("КОЛИЧЕСТВО: " + c.getInt(qIndex));
        final int k = c.getInt(qIndex);
        final long id = c.getLong(0);
        p = k - 1;
        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (p >= 0) {
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            //ContentValues contentValues = new ContentValues();

                            db.execSQL("UPDATE Goods SET Quantity = ? WHERE id = ?", new String[]{String.valueOf(p), String.valueOf(id)});
                            //contentValues.put(MyDBHelper.KEY_QUANTITY, qIndex - 1);
                            //db.update(MyDBHelper.TABLE_GOODS, contentValues, MyDBHelper.KEY_ID + "=" + idIndex, null);
                            Cursor c = db.query("Goods", null, null, null, null, null, null);
                            swapCursor(c);
                            p = p - 1;
                        }
                    }, del * 1000);
                }
                else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("DELETE FROM Goods WHERE id = ?", new String[]{String.valueOf(id)});
                    Cursor c = db.query("Goods", null, null, null, null, null, null);
                    c.close();
                }
            }
        });
    }


}

class PagerVH extends RecyclerView.ViewHolder {
    TextView textView;
    TextView textView2;
    TextView textView3;
    Button button;

    public PagerVH(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        textView2 = itemView.findViewById(R.id.textView2);
        textView3 = itemView.findViewById(R.id.textView3);
        button = itemView.findViewById(R.id.button);
    }
}
