package com.example.appsach.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appsach.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.adapterThongKe;
import model.Son.Item;

public class adminThongKe extends AppCompatActivity {

    private ArrayList<Item> arrItem;
    private ArrayList<Item> arrAllItem;
    private ListView listViewTk;
    private TextView txtTong,txtTiLe;

    private ImageView imgBack;

    private Item item, allItem;
    private int a,b,d;

    double tongall= 0,tong = 0,tong10 = 0;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_thongke);
        mapping();
        getData();
        for (Item i : arrItem) {
            tong10+= i.getDoanhThu();
        }
        for (Item i : arrAllItem) {
            tong+= i.getDoanhThu();
        }

        tongall =Math.round((tong10/tong)*100*100/100);

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);

        String tongVn = vn.format(tong);
        String tongAll = vn.format(tongall);
        txtTong.setText(tongVn);
        txtTiLe.setText(tongAll+"%");

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminThongKe.this,MainAdmin.class));
            }
        });
    }
    @SuppressLint({"Range", "NotifyDataSetChanged"})
    private void getData()
    {
        arrItem = new ArrayList<>();
        arrAllItem = new ArrayList<>();

        sqlite s = new sqlite(adminThongKe.this,R.string.databaseName+"",null,1);
        Cursor c = s.getData("SELECT * FROM book ORDER BY  luotmua DESC LIMIT 10");

        while (c.moveToNext())
            {
            byte[] bytes = c.getBlob(c.getColumnIndex("hinhanh"));
            a = c.getInt(c.getColumnIndex("luotmua"));
            b = Integer.parseInt(c.getString(c.getColumnIndex("gia")));
            d = a * b;
            item = new Item(c.getInt(c.getColumnIndex("luotmua")),c.getString(c.getColumnIndex("tieude")),
                    c.getString(c.getColumnIndex("gia")),BitmapUtils.getImage(bytes),d);
            arrItem.add(item);
        }
        adapterThongKe adapter = new adapterThongKe(adminThongKe.this,R.layout.admin_item_thongke,arrItem);
        listViewTk.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        Cursor c2 = s.getData("SELECT * FROM book");
        while (c2.moveToNext())
        {
            a = c2.getInt(c2.getColumnIndex("luotmua"));
            b = Integer.parseInt(c2.getString(c2.getColumnIndex("gia")));
            d = a * b;
            allItem = new Item(d,c2.getInt(c2.getColumnIndex("id_book")));
            arrAllItem.add(allItem);
        }
    }

    private void mapping() {
        listViewTk = findViewById(R.id.listThongKe);
        txtTong = findViewById(R.id.tztThuNhap);
        txtTiLe = findViewById(R.id.txtTong);
        imgBack = findViewById(R.id.imgBack2);

    }
}
