package com.example.appsach.Profile;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appsach.R;
import com.example.appsach.StartProject.LoginActivity;


import java.util.ArrayList;

import SQLite.sqlite;
import adapter.Vanh.AdapterDonhang;
import model.Vanh.Donhang;

public class Quanlidon extends Activity {
    ListView lstDonhang;
    AdapterDonhang donHangAdapter;
    ArrayList<Donhang> arrDonhang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quanlidon);
        lstDonhang = findViewById(R.id.lstDonhang);
        arrDonhang = new ArrayList<>();
        donHangAdapter = new AdapterDonhang(Quanlidon.this, R.layout.item_quanlidon, arrDonhang);

        sqlite s = new sqlite(Quanlidon.this,"cart.db",null,1);
        Cursor cursor = s.getData("SELECT * FROM tbl_hoadon");
        while (cursor.moveToNext()){
            arrDonhang.add(new Donhang(cursor.getInt(1), cursor.getInt(2), 0));
        }
        lstDonhang.setAdapter(donHangAdapter);
        donHangAdapter.notifyDataSetChanged();
    }
}
