package com.example.appsach.admin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.example.appsach.R;
import java.util.ArrayList;
import SQLite.sqlite;
import adapter.Vanh.AdapterAdminDonhang;
import model.Vanh.Donhang;

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