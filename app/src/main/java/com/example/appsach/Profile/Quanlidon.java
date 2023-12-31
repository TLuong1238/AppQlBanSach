package com.example.appsach.Profile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.example.appsach.R;
import java.util.ArrayList;
import SQLite.sqlite;
import adapter.Vanh.AdapterDonhang;
import model.Vanh.Donhang;
import model.user;

public class Quanlidon extends Activity {
    private ListView lstDonhang;
    private AdapterDonhang donHangAdapter;
    private ArrayList<Donhang> arrDonhang;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quanlidon);
        lstDonhang = findViewById(R.id.lstDonhang);
        sp = getSharedPreferences("LoginData",MODE_PRIVATE);
        user user = new user(sp.getInt("id",0),sp.getString("name",""),sp.getString("email",""),
                sp.getString("pass",""),sp.getString("phone",""));
        int id_tk = user.getId_user();
        arrDonhang = new ArrayList<>();
        donHangAdapter = new AdapterDonhang(Quanlidon.this, R.layout.item_quanlidon, arrDonhang);
        sqlite s = new sqlite(Quanlidon.this,R.string.databaseName+"",null,1);
        Cursor cursor = s.getData("SELECT * FROM tbl_hoadon WHERE id_taikhoan = '"+ id_tk +"' ");
        while (cursor.moveToNext()){
            arrDonhang.add(new Donhang(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3)));
        }
        lstDonhang.setAdapter(donHangAdapter);
        donHangAdapter.notifyDataSetChanged();
    }
}