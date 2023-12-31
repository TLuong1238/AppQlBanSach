package com.example.appsach.admin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.appsach.R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import SQLite.sqlite;
import adapter.Vanh.AdapterAdminDoanhthu;
import model.Vanh.Donhang;

public class adminDoanhthu extends Activity {
    private ListView lstDoanhthu;
    private AdapterAdminDoanhthu donHangAdapter;
    private ArrayList<Donhang> arrDonhang;
    private TextView txtDoanhthu;
    private ImageView btnRollback;
    private int doanhthu = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_doanhthu);
        lstDoanhthu = findViewById(R.id.lstDoanhthu);
        txtDoanhthu = findViewById(R.id.txtDoanhthu);
        btnRollback = findViewById(R.id.btnRollback);
        arrDonhang = new ArrayList<>();


        sqlite s = new sqlite(adminDoanhthu.this,R.string.databaseName+"",null,1);
        s.QueryData(("CREATE TABLE IF NOT EXISTS tbl_chitietdonhang(id_chitietdonhang INTEGER PRIMARY KEY AUTOINCREMENT, ma_donhang INTEGER, id_taikhoan INTEGER, tensanpham TEXT, soluong INTEGER, gia INTEGER)"));
        Cursor cursor = s.getData("SELECT * FROM tbl_hoadon WHERE tinhtrang = 0");
        Cursor getDoanhthu = s.getData("SELECT tbl_chitietdonhang.gia FROM tbl_hoadon JOIN tbl_chitietdonhang ON tbl_hoadon.ma_donhang = tbl_chitietdonhang.ma_donhang WHERE tbl_hoadon.tinhtrang = 0;");


        while (cursor.moveToNext()){
            arrDonhang.add(new Donhang(cursor.getInt(1), cursor.getInt(2), 0));
        }
        while (getDoanhthu.moveToNext()){
            doanhthu += getDoanhthu.getInt(0);
        }
        donHangAdapter = new AdapterAdminDoanhthu(adminDoanhthu.this, R.layout.admin_item_doanhthu, arrDonhang);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtDoanhthu.setText(decimalFormat.format(doanhthu)+ " vnd");
        lstDoanhthu.setAdapter(donHangAdapter);
        donHangAdapter.notifyDataSetChanged();

        btnRollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminDoanhthu.this, MainAdmin.class);
                startActivity(intent);
            }
        });
    }

}