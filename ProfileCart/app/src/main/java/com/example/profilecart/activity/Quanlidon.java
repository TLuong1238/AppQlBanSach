package com.example.profilecart.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.profilecart.R;

import java.util.ArrayList;

public class Quanlidon extends Activity {
    ListView lst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quanlidon);
        lst = findViewById(R.id.lst);
        ArrayList<String> arr= new ArrayList<>();
        ArrayAdapter<String> adapter= new ArrayAdapter<>(Quanlidon.this,android.R.layout.simple_list_item_1,arr);
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.profilecart/databases/cart.db", null);
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_chitietdonhang", null);
        while (cursor.moveToNext()){
            arr.add("Sản phẩm: " + cursor.getString(3) + " \nSố lượng: " + cursor.getString(4) + " \nGiá tiền: " + cursor.getString(5));
        }
        lst.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
