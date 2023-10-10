package com.example.appsach.Profile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appsach.R;


import java.util.ArrayList;

public class Chitiethoadon extends Activity {
    ListView lstChitietdonhang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietdonhang);
        lstChitietdonhang = findViewById(R.id.lstChitietdonhang);

        ArrayList<String> arr= new ArrayList<>();
        ArrayAdapter<String> adapter= new ArrayAdapter<>(Chitiethoadon.this, android.R.layout.simple_list_item_1, arr);
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.profilecart/databases/cart.db", null);

        Intent intent = getIntent();
        String ma = intent.getStringExtra("ma_donhang");

        Cursor cursor = db.rawQuery("SELECT * FROM tbl_chitietdonhang WHERE ma_donhang = '" + ma +"'", null);
//        Cursor cursor = db.rawQuery("SELECT * FROM tbl_chitietdonhang", null);
        while (cursor.moveToNext()){
            arr.add("Sản phẩm: " + cursor.getString(3) + " \nSố lượng: " + cursor.getString(4) + " \nGiá tiền: " + cursor.getString(5));
        }
        lstChitietdonhang.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
