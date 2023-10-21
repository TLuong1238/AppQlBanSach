package com.example.appsach.admin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appsach.Profile.GioHang;
import com.example.appsach.R;
import com.example.appsach.StartProject.LoginActivity;


import java.util.ArrayList;

import SQLite.sqlite;
import adapter.Vanh.AdapterAdminDonhang;
import adapter.Vanh.AdapterDonhang;
import model.Vanh.Donhang;
import model.user;

public class adminQuanlidon extends Activity {
    private ListView lstDonhang;
    private AdapterAdminDonhang donHangAdapter;
    private ArrayList<Donhang> arrDonhang;
    private ImageView btnRollback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_quanlidon);
        lstDonhang = findViewById(R.id.lstDonhang);
        btnRollback = findViewById(R.id.btnRollback);

        arrDonhang = new ArrayList<>();
        donHangAdapter = new AdapterAdminDonhang(adminQuanlidon.this, R.layout.admin_item_donhang, arrDonhang);

        sqlite s = new sqlite(adminQuanlidon.this,R.string.databaseName+"",null,1);
        Cursor cursor = s.getData("SELECT * FROM tbl_hoadon WHERE tinhtrang = 1");

        while (cursor.moveToNext()){
            arrDonhang.add(new Donhang(cursor.getInt(1), cursor.getInt(2), 1));
        }
        lstDonhang.setAdapter(donHangAdapter);
        donHangAdapter.notifyDataSetChanged();

        btnRollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminQuanlidon.this, MainAdmin.class);
                startActivity(intent);
            }
        });
    }

}