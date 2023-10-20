package com.example.appsach.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.appsach.R;
import com.example.appsach.StartProject.LoginActivity;

public class MainAdmin extends AppCompatActivity {
    private Button btnLogout,btnChangePass;
    private String pass,name, email;
    private CardView QlUser,QlOrder,QlCate,QlTop,QlProduct,QlMoney;
    private modelAdmin admin;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        mapping();
        sp = getSharedPreferences("LoginData",MODE_PRIVATE);
        editor = sp.edit();
         pass = sp.getString("passAdmin","");
         name = sp.getString("nameAdmin","");
         email = sp.getString("emailAdmin","");
        admin = new modelAdmin(name,pass,email);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainAdmin.this);
                dialog.setTitle("Thông báo");
                dialog.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.clear();
                        editor.apply();
                        startActivity(new Intent(MainAdmin.this, LoginActivity.class));
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

        QlCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainAdmin.this, adminDanhMuc.class));
                finish();

            }
        });

        QlProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainAdmin.this, adminBookList.class));
                finish();
            }
        });

        //
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainAdmin.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_xac_nhan,null);
                EditText edtXacNhan= view.findViewById(R.id.edtXacNhan);
                alert.setView(view);
                alert.setTitle("Thông báo");
                alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = edtXacNhan.getText().toString().trim();
                        if(str.equals(pass))
                        {
                            Toast.makeText(MainAdmin.this, "Mật khẩu chính xác!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainAdmin.this, changePassAdmin.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putSerializable("objectAdmin",admin);
                            i.putExtras(bundle1);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(MainAdmin.this, "Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
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
        QlOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this, adminQuanlidon.class));
                finish();
            }
        });
        QlMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,adminDoanhthu.class));
                finish();
            }
        });
        QlUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this, adminQlUser.class));
                finish();
            }
        });
        QlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAdmin.this,adminThongKe.class));
                finish();
            }
        });


    }

    private void mapping()
    {
        btnLogout = findViewById(R.id.btnLogoutAdmin);
        btnChangePass =findViewById(R.id.btnChangePassAdmin);
        QlUser = findViewById(R.id.QlUser);
        QlCate= findViewById(R.id.QlCate);
        QlProduct = findViewById(R.id.QlProduct);
        QlMoney = findViewById(R.id.QlMoney);
        QlTop = findViewById(R.id.QlTop);
        QlOrder = findViewById(R.id.QlOrder);
    }




}
