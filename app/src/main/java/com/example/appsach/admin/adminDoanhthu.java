package com.example.appsach.admin;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.appsach.R;


import java.text.DecimalFormat;
import java.util.ArrayList;

import SQLite.sqlite;
import adapter.Vanh.AdapterAdminDoanhthu;
import adapter.Vanh.AdapterAdminDonhang;
import model.Vanh.Donhang;

public class adminDoanhthu extends Activity {
    ListView lstDoanhthu;
    AdapterAdminDoanhthu donHangAdapter;
    ArrayList<Donhang> arrDonhang;
    TextView txtDoanhthu;
    int doanhthu = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_doanhthu);
        lstDoanhthu = findViewById(R.id.lstDoanhthu);
        txtDoanhthu = findViewById(R.id.txtDoanhthu);

        arrDonhang = new ArrayList<>();
        donHangAdapter = new AdapterAdminDoanhthu(adminDoanhthu.this, R.layout.admin_item_doanhthu, arrDonhang);

        sqlite s = new sqlite(adminDoanhthu.this,"cart.db",null,1);
        Cursor cursor = s.getData("SELECT * FROM tbl_hoadon WHERE tinhtrang = 0");
        Cursor getDoanhthu = s.getData("SELECT tbl_chitietdonhang.gia FROM tbl_hoadon JOIN tbl_chitietdonhang ON tbl_hoadon.ma_donhang = tbl_chitietdonhang.ma_donhang WHERE tbl_hoadon.tinhtrang = 0;");


        while (cursor.moveToNext()){
            arrDonhang.add(new Donhang(cursor.getInt(1), cursor.getInt(2), 0));
        }
        while (getDoanhthu.moveToNext()){
            doanhthu += getDoanhthu.getInt(0);
        }

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtDoanhthu.setText(decimalFormat.format(doanhthu)+ " vnd");
        lstDoanhthu.setAdapter(donHangAdapter);
        donHangAdapter.notifyDataSetChanged();
    }

}