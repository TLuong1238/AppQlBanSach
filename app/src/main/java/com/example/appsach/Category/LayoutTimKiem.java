package com.example.appsach.Category;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsach.R;
import java.util.ArrayList;

import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.Son.DisplayItemAdapter;
import adapter.Son.RecyclerItemClickListener;
import model.Son.Item;

public class LayoutTimKiem extends Activity {
    private ImageView imgView_back, imgView_option;

    private EditText ed_search;

    private RecyclerView recyclerV_displayItem;

    private DisplayItemAdapter adapter;

    private sqlite sqLiteHelper ;
    private Cursor cursor;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tim_kiem);
        sqLiteHelper = new sqlite(this,R.string.databaseName+"",null,1);
        sqLiteHelper.updateDB();
        anhxa();
//        sqLiteHelper.createTable();
   //     insertData();
        cursor = sqLiteHelper.getData("select * from book");
        recyclerV_displayItem = findViewById(R.id.recyclerV_DisplayItem);
        adapter = new DisplayItemAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerV_displayItem.setLayoutManager(gridLayoutManager);
        adapter.setData(getListData());
        recyclerV_displayItem.setAdapter(adapter);

        setOnClickItemRecycleView();
        imgView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LayoutTimKiem.this, cateFragment.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa(){
        ed_search = findViewById(R.id.ed_timKiem1);
        recyclerV_displayItem = findViewById(R.id.recyclerV_DisplayItem);
        imgView_back = findViewById(R.id.Search_img_back);
    }

    private void setOnClickItemRecycleView(){
        recyclerV_displayItem.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerV_displayItem, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView ten = view.findViewById(R.id.tv_name1);
                Intent intent = new Intent(LayoutTimKiem.this,LayoutInfoItem.class);
                String name = ten.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putInt("idlayout",R.layout.layout_tim_kiem);
                bundle.putString("name",name);
                intent.putExtra("name_item",bundle);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }

    private ArrayList<Item> getListData(){
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("key");
        String key_word = bundle.getString("key_word");
        ed_search.setText(key_word);
        cursor = sqLiteHelper.getData("Select * from book where tieude like '%"  + key_word + "%'");
        int i = 0;
        ArrayList<Item> arr = new ArrayList<>();
        while (cursor.moveToNext()){
            byte[] temp = cursor.getBlob(6);
            arr.add(new Item(cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(9)), BitmapUtils.getImage(temp)));
//            Toast.makeText(this, arr.get(i).getImage().toString(),Toast.LENGTH_LONG).show();
            i++;
        }

        return arr;
    }

}
