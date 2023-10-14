package com.example.appsach.Profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsach.Home.MainActivity;
import com.example.appsach.R;
import com.example.appsach.StartProject.LoginActivity;

import SQLite.sqlite;
import model.user;

public class ThongTin extends AppCompatActivity {
    private TextView txtInfoName,txtInfoEmail,txtInfoSdt;
    Button btnFix,btnDelete;
    user GetUser,u;
    int count = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtintk);
        mapping();
        //
        Bundle bundle = getIntent().getExtras();
        GetUser = (user) bundle.get("object_user");
        //
        sqlite s = new sqlite(ThongTin.this,R.string.databaseName+"",null,1);

        Cursor c = s.getData("SELECT * From user WHERE email ='"+GetUser.getEmail_user()+"' AND password ='"+GetUser.getPassword()+"'");
        while (c.moveToNext()){
           u = new user(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
        }
        //
        txtInfoName.setText(u.getName_user());
        txtInfoEmail.setText(u.getEmail_user());
        txtInfoSdt.setText(u.getSdt());

        //
        btnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ThongTin.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_xac_nhan,null);
                EditText edtXachNhan= view.findViewById(R.id.edtXacNhan);
                alert.setView(view);
                alert.setTitle("Thông báo");
                alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = edtXachNhan.getText().toString();
                        if(str.equals(GetUser.getPassword()))
                        {
                            Toast.makeText(ThongTin.this, "Mật khẩu chính xác!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ThongTin.this,ChangeInfo.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putSerializable("object_user",GetUser);
                            i.putExtras(bundle1);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(ThongTin.this, "Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                        }
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
        //
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ThongTin.this);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Bạn có chắc chắn muốn xóa tài khoản không?");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqlite s = new sqlite(ThongTin.this,"TestApp.db",null,1);
                        s.QueryData("DELETE FROM user WHERE  email ='"+GetUser.getEmail_user()+"'OR name ='"+GetUser.getName_user()+"'");

                        Cursor c= s.getData("SELECT * From user WHERE email ='"+GetUser.getEmail_user()+"' AND name ='"+GetUser.getName_user()+"' ");
                        while (c.moveToNext()){
                            count++;
                        }
                        if(count > 0)
                        {
                            Toast.makeText(ThongTin.this, "Xóa tài khoa thất bại!", Toast.LENGTH_SHORT).show();
                        } else
                        {
                            Toast.makeText(ThongTin.this, "Xóa tài khoản thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ThongTin.this,LoginActivity.class));
                            finish();
                        }
                    }
                });
                dialog.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });



    }

    private void mapping()
    {
        txtInfoName = findViewById(R.id.txtInfoName);
        txtInfoEmail = findViewById(R.id.txtInfoEmail);
        txtInfoSdt = findViewById(R.id.txtInfoSdt);
        btnFix = findViewById(R.id.btnFix);
        btnDelete = findViewById(R.id.btnDelete);

    }

}
