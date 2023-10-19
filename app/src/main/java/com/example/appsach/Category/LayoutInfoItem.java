package com.example.appsach.Category;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import com.example.appsach.Home.MainActivity;
import com.example.appsach.R;

import java.util.ArrayList;
import java.util.List;

import SQLite.BitmapUtils;
import SQLite.sqlite;
import model.Son.Photo;
import model.user;

public class LayoutInfoItem extends Activity {

    TextView Info_img_back;

    ImageView image;
    TextView tenSach, gia,  nhaph, nhaxb, tomtat, danhmuc;

    Cursor cursor;

    Bundle bundle;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    Intent intent;
    Button insertIntoCart;

    private sqlite database;

    @SuppressLint("Range")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_item);
        database = new sqlite(LayoutInfoItem.this,R.string.databaseName+"",null,1);
        anhxa();


        intent = getIntent();
        bundle = intent.getBundleExtra("name_item");
        getData();
        cursor.moveToFirst();
        byte[] img = cursor.getBlob(cursor.getColumnIndex("hinhanh"));
        image.setImageBitmap(BitmapUtils.getImage(img));
        tenSach.setText(cursor.getString(cursor.getColumnIndex("tieude")));
        gia.setText(cursor.getString(cursor.getColumnIndex("gia")));
        nhaph.setText(cursor.getString(cursor.getColumnIndex("ten_nhaph")));
        nhaxb.setText(cursor.getString(cursor.getColumnIndex("ten_nhaxb")));
        tomtat.setText(cursor.getString(cursor.getColumnIndex("tomtat")));
        danhmuc.setText(cursor.getString(cursor.getColumnIndex("ten_danhmuc")));


        Info_img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int idLayout;
                idLayout = bundle.getInt("idlayout");
                if(idLayout == R.layout.layout_tim_kiem){
                    Intent it = new Intent(LayoutInfoItem.this,LayoutTimKiem.class);
                    String key = bundle.getString("name");
                    Bundle bb = new Bundle();
                    bb.putString("key_word",key);
                    it.putExtra("key",bb);
                    startActivity(it);
                }
                if(idLayout == R.layout.layout_danh_muc){
                    //Co bug
                    Intent it = new Intent(LayoutInfoItem.this, MainActivity.class);
                    startActivity(it);

                }

            }
        });
        insertIntoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cs;
                cs = database.getData("select * from gio_hang");
                boolean check = true;

                while (cs.moveToNext()){
                    if(tenSach.getText().toString().equalsIgnoreCase(cs.getString(cs.getColumnIndex("tensach")))){
                        check =false;
                    }
                }
                if(!check) {
                    Toast.makeText(LayoutInfoItem.this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_LONG).show();
                }
                else {
                    user user = new user(sp.getInt("id",0),sp.getString("name",""),sp.getString("email",""),
                            sp.getString("pass",""),sp.getString("phone",""));
                    int id_tk = user.getId_user();

                    database.QueryData("Delete * from gio_hang");

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id_taikhoan",id_tk);
                    contentValues.put("tensach",cursor.getString(cursor.getColumnIndex("tieude")));
                    contentValues.put("hinhanh",cursor.getBlob(cursor.getColumnIndex("hinhanh")));
                    contentValues.put("gia",cursor.getString(cursor.getColumnIndex("gia")));

                    int prv = cs.getCount();
                    SQLiteDatabase db = database.getWritableDatabase();
                    db.insert("gio_hang",null,contentValues);
                    cs = database.getData("select * from gio_hang");
                    if(prv == cs.getCount()-1){
                        Toast.makeText(LayoutInfoItem.this, "Thêm vào giỏ hàng thành công" + cs.getCount(), Toast.LENGTH_LONG).show();
                    }
                }
                cs.close();
            }
        });
    }


    private void getData(){
        String key_word = bundle.getString("name");
        cursor = database.getData("select book.hinhanh, book.tieude, book.hinhanh, book.gia, book.tomtat, nha_phat_hanh.ten_nhaph, nha_xuatban.ten_nhaxb, danh_muc.ten_danhmuc from book " +
                " join danh_muc on book.id_danhmuc = danh_muc.id_danhmuc " +
                " join nha_phat_hanh on book.id_nhaph = nha_phat_hanh.id_nhaph " +
                " join nha_xuatban on book.id_nhaxb = nha_xuatban.id_nhaxb " +
                "where book.tieude = '"+key_word+"';");

    }
    private void anhxa(){
        image = findViewById(R.id.image_item);
        tenSach = findViewById(R.id.tv_titleOfBook);
        gia = findViewById(R.id.tv_priceBook);
        nhaph = findViewById(R.id.tv_nhaph);
        nhaxb = findViewById(R.id.tv_nhaxb);
        tomtat = findViewById(R.id.tv_tomtat);
        danhmuc = findViewById(R.id.tv_danhMucBook);
        Info_img_back = findViewById(R.id.Info_img_back);
        insertIntoCart = findViewById(R.id.btn_insertIntoCart);

    }


}
