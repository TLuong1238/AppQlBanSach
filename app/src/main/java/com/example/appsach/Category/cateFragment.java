package com.example.appsach.Category;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.appsach.R;

import java.util.ArrayList;
import java.util.Random;

import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.Son.DanhMucAdapter;
import adapter.Son.ItemAdapter;
import adapter.Son.RecyclerItemClickListener;
import model.Son.DanhMuc;
import model.Son.Item;



public class cateFragment extends Fragment {

    private RecyclerView recyclerView_item;
    private ListView listView_DanhMuc;
    private ItemAdapter adapter_item;
    private ArrayList<DanhMuc> arrDanhMuc;
    private EditText ed_Search;
    private Cursor cursor;
    private ArrayList<Item> arr;
    private ImageView img_searching;
    private DanhMucAdapter danhMucAdapter;

    private sqlite database;

    public cateFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_danh_muc, container, false);
        database = new sqlite(getContext(), R.string.databaseName + "", null, 1);

        //Tạo bảng
        arrDanhMuc = new ArrayList<>();
        ed_Search = view.findViewById(R.id.ed_timKiem1);
        listView_DanhMuc = view.findViewById(R.id.lv_danh_muc);
        recyclerView_item = view.findViewById(R.id.recyclerV_Item);
        img_searching = view.findViewById(R.id.img_searching1);

        //danh muc
        danhMucAdapter = new DanhMucAdapter(getContext(), R.layout.layout_custom_danhmuc, arrDanhMuc);
        getDataToListViewDanhMuc();
        listView_DanhMuc.setAdapter(danhMucAdapter);

        //recycleView
        adapter_item = new ItemAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

//        insertSubData();


        getListData();
        recyclerView_item.requestFocus();
        recyclerView_item.setLayoutManager(gridLayoutManager);
        adapter_item.setData(arr);
        recyclerView_item.setAdapter(adapter_item);

        //ed_search
        searching();

        //setOnItemClickListener for RecycleView
        recyclerView_item.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView_item, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView ten = view.findViewById(R.id.tv_name);
                String name = ten.getText().toString();
                Intent intent = new Intent(getContext(), LayoutInfoItem.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putInt("idlayout", R.layout.layout_danh_muc);
                intent.putExtra("name_item", bundle);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        ////setOnItemClickListener for ListView
        listView_DanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView danhMuc = view.findViewById(R.id.tv_danhmuc);
                String search = danhMuc.getText().toString();
                arr.clear();
                updateRecycleViewUpToDanhMuc(search);
                adapter_item.setData(arr);
            }
        });

        ed_Search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) return false;
                if (i == KeyEvent.KEYCODE_ENTER) {
                    String key_word = ed_Search.getText().toString();
                    finalSeaching(key_word);
                    return true;
                }
                return false;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void searching() {
        img_searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ed_Search.getText().toString();
                finalSeaching(s);
            }
        });
    }

    private void finalSeaching(String s) {
        Intent intent = new Intent(getContext(), LayoutTimKiem.class);
        Bundle bundle = new Bundle();
        bundle.putString("key_word", s);
        intent.putExtra("key", bundle);
        ed_Search.setText("");
        startActivity(intent);
    }


    @SuppressLint("Range")
    private void getDataToListViewDanhMuc() {
        cursor = database.getData("select * from danh_muc");
        while (cursor.moveToNext()) {
            arrDanhMuc.add(new DanhMuc(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_danhmuc"))), cursor.getString(cursor.getColumnIndex("ten_danhmuc"))));
        }
        danhMucAdapter.notifyDataSetChanged();
    }

    @SuppressLint("Range")
    private void updateRecycleViewUpToDanhMuc(String s) {
        arr = new ArrayList<Item>();
        cursor = database.getData("select * from book " + "join danh_muc on danh_muc.id_danhmuc = book.id_danhmuc " + "where danh_muc.ten_danhmuc = '" + s + "';");
        while (cursor.moveToNext()) {
            byte[] temp = cursor.getBlob(6);
            arr.add(new Item(cursor.getString(1), BitmapUtils.getImage(temp)));
        }
    }

    private void getListData() {
        arr = new ArrayList<Item>();
        cursor = database.getData("select * from book");
        while (cursor.moveToNext()) {
            byte[] temp = cursor.getBlob(6);
            arr.add(new Item(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(9)), BitmapUtils.getImage(temp)));
        }
    }

}
