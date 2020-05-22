package com.example.laba7;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends Activity {
    MyDBHelper dbHelper_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_activity);
        TextView text_id = (TextView)findViewById(R.id.text_id);
        TextView text_name = (TextView)findViewById(R.id.text_name);
        TextView text_price = (TextView)findViewById(R.id.text_price);
        TextView text_quant = (TextView)findViewById(R.id.text_quant);
        text_id.append("№\n");
        text_name.append("НАИМЕНОВАНИЕ \n");
        text_price.append("СТОИМОСТЬ \n");
        text_quant.append("КОЛИЧЕСТВО \n");
        dbHelper_2 = new MyDBHelper(this);
        SQLiteDatabase database_3 = dbHelper_2.getWritableDatabase();
        String query = "SELECT " + MyDBHelper.KEY_ID + ", "
                + MyDBHelper.KEY_NAME + ", " + MyDBHelper.KEY_PRICE + ", " + MyDBHelper.KEY_QUANTITY + " FROM " + MyDBHelper.TABLE_GOODS;
        Cursor cursor2 = database_3.rawQuery(query, null);
        int idIndex = cursor2.getColumnIndex(MyDBHelper.KEY_ID);
        int nameIndex = cursor2.getColumnIndex(MyDBHelper.KEY_NAME);
        int priceIndex = cursor2.getColumnIndex(MyDBHelper.KEY_PRICE);
        int quantIndex = cursor2.getColumnIndex(MyDBHelper.KEY_QUANTITY);
        //catCursor.moveToFirst();
        String id_2;
        String name_2;
        String price_2;
        String quant_2;
        while (cursor2.moveToNext()) {

            id_2 = cursor2.getString(idIndex);
            text_id.append(id_2 + " \n");
            name_2 = cursor2.getString(nameIndex);
            text_name.append(name_2 + " \n");
            price_2 = cursor2.getString(priceIndex);
            text_price.append(price_2 + " \n");
            quant_2 = cursor2.getString(quantIndex);
            text_quant.append(quant_2 + " \n");
        }
        cursor2.close();
    }
}
