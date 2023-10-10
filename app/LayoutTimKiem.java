package com.application.test_baitaplon.View;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.test_baitaplon.DisplayItemAdapter;
import com.application.test_baitaplon.Model.Item;
import com.application.test_baitaplon.R;

import java.util.ArrayList;

public class LayoutTimKiem extends Activity {
    private ImageView imgView_back, imgView_option;

    private EditText ed_search;

    private RecyclerView recyclerV_displayItem;

    private DisplayItemAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tim_kiem);
        recyclerV_displayItem = findViewById(R.id.recyclerV_DisplayItem);
        adapter = new DisplayItemAdapter(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerV_displayItem.setLayoutManager(gridLayoutManager);
        adapter.setData(getListData());
        recyclerV_displayItem.setAdapter(adapter);
    }

    private ArrayList<Item> getListData(){
        ArrayList<Item> arr = new ArrayList<>();
        arr.add(new Item(R.drawable.sach1,"Chuyến đi của thanh xuân","200.000"));
        arr.add(new Item(R.drawable.sach2,"Chạy trốn mặt trời", "150.000"));
        arr.add(new Item(R.drawable.sach3,"All things you know about me are wrong", "80.000"));
        arr.add(new Item(R.drawable.sach4,"Những loài hoa có gai", "243.000"));

        arr.add(new Item(R.drawable.sach1,"Chuyến đi của thanh xuân","200.000"));
        arr.add(new Item(R.drawable.sach2,"Chạy trốn mặt trời", "150.000"));
        arr.add(new Item(R.drawable.sach3,"All things you know about me are wrong", "80.000"));
        arr.add(new Item(R.drawable.sach4,"Những loài hoa có gai", "243.000"));

        arr.add(new Item(R.drawable.sach1,"Chuyến đi của thanh xuân","200.000"));
        arr.add(new Item(R.drawable.sach2,"Chạy trốn mặt trời", "150.000"));
        arr.add(new Item(R.drawable.sach3,"All things you know about me are wrong", "80.000"));
        arr.add(new Item(R.drawable.sach4,"Những loài hoa có gai", "243.000"));
        return arr;
    }
}
