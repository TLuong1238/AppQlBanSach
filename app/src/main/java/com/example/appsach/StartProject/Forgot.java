package com.example.appsach.StartProject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsach.Profile.ChangePass;
import com.example.appsach.R;

import SQLite.sqlite;
import model.user;

public class Forgot extends AppCompatActivity {
    private EditText edtConfirmEmail,edtConfirmName;
    private Button btnConfirm,btnBackToLogin;

    int index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        mapping();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder db = new AlertDialog.Builder(Forgot.this);
                if(ktr()==true){
                    db.setMessage("Yêu cầu điền đầy đủ dữ liệu");
                    db.setTitle("Thông báo");
                    db.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    db.show();

                } else
                {
                    user u = null;
                    sqlite s = new sqlite(Forgot.this, "TestApp.db", null, 1);
                    Cursor c = s.getData("SELECT * FROM user WHERE email ='"+edtConfirmEmail.getText().toString().trim()+"'OR name ='"+edtConfirmName.getText().toString().trim()+"'");
                    while (c.moveToNext()){
                        index++;
                        u = new user(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                    }
                    if(index==0){
                        Toast.makeText(Forgot.this, "Tài khoản không tồn tại!", Toast.LENGTH_SHORT).show();
                        xoatr();
                        edtConfirmEmail.requestFocus();
                    }else {
                        Toast.makeText(Forgot.this, "Tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Forgot.this, ChangePass.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("objectUser",u);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                    index=0;

                }
            }
        });
        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forgot.this,LoginActivity.class));
            }
        });
    }
//

    private void mapping() {
        edtConfirmName = findViewById(R.id.edtConfirmName);
        edtConfirmEmail = findViewById(R.id.edtConfirmMail);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBackToLogin = findViewById(R.id.btnBackToLogin);
    }
    Boolean ktr(){
        boolean a;
        String ktr_ten=edtConfirmName.getText().toString();
        String ktr_mail=edtConfirmEmail.getText().toString();
        if(ktr_ten.equals("")){
            a=true;
        }else if(ktr_mail.equals("")){
            a=true;
        }else{
            a=false;
        }
        return a;
    }
    private void xoatr(){
        edtConfirmName.setText("");
        edtConfirmEmail.setText("");
    }


}
