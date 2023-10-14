package com.example.appsach.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsach.R;
import com.example.appsach.StartProject.SignupActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import SQLite.sqlite;
import adapter.adapterQlUser;
import model.user;

public class adminQlUser extends AppCompatActivity {
    private ListView lvQlUser;
    private ImageView imgBack;
    private ArrayList<user> listUser;
    private EditText edtNameCus,edtEmailCus,edtSdtCus,edtPassCus;

    private FloatingActionButton floatAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_qluser);
        mapping();
        getData();


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder alert = new AlertDialog.Builder(adminQlUser.this);
                LayoutInflater layoutInflater = getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.admin_dia_changeinfo, null);
                alert.setView(view);

                edtNameCus = view.findViewById(R.id.edtCusName);
                edtEmailCus = view.findViewById(R.id.edtCusMail);
                edtSdtCus = view.findViewById(R.id.edtCusPhone);
                edtPassCus = view.findViewById(R.id.edtCusPass);

                alert.setTitle("Thông báo");
                alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqlite s = new sqlite(adminQlUser.this, R.string.databaseName+"", null, 1);
                        s.QueryData("INSERT INTO user VALUES (null,'"+edtNameCus.getText()+"','"+edtEmailCus.getText()+"','"+edtPassCus.getText()+"','"+edtSdtCus.getText()+"')" );
                        Toast.makeText(adminQlUser.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                });
                alert.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(adminQlUser.this,MainAdmin.class));
            }
        });
    }

    public void getData()
    {
        listUser = new ArrayList<>();
        sqlite s = new sqlite(adminQlUser.this,R.string.databaseName+"",null,1);
        Cursor c = s.getData("SELECT * FROM user");
        while (c.moveToNext())
        {
            user u = new user(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
            listUser.add(u);
        }

        adapterQlUser adapter = new adapterQlUser(adminQlUser.this,R.layout.admin_item_user,listUser);
        lvQlUser.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void mapping() {
        floatAdd =findViewById(R.id.floatAdd);
        lvQlUser = findViewById(R.id.listCustomer);
        imgBack =findViewById(R.id.imgBack);
    }

}
