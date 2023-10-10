package com.example.appsach.StartProject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsach.Home.MainActivity;
import com.example.appsach.Profile.profileFragment;
import com.example.appsach.R;

import SQLite.sqlite;
import model.user;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edt_email_login,edt_pass_login;
    private TextView txtToSingup,txtForgot;
    private String name,email,pass,sdt;
    private int id;
    int index = 0;
    CheckBox ckRemember;
    SharedPreferences sp;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        //
        sp = getSharedPreferences("LoginData",MODE_PRIVATE);
        editor = sp.edit();
        boolean login = sp.getBoolean("loggedIn",false);
        if(login)
        {
            Toast.makeText(this, "Chào mừng: "+sp.getString("name","")+" quay trở lại!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        //
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                if(ktr()==true){
                    builder.setTitle("Thông báo");
                    builder.setMessage("Chưa điền đủ thông tin!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edt_email_login.requestFocus();
                        }
                    });
                    builder.show();

                }else{
                    sqlite s = new sqlite(LoginActivity.this,"TestApp.db",null,1);

                    Cursor c = s.getData("SELECT * From user WHERE email ='"+edt_email_login.getText()+"' AND password ='"+edt_pass_login.getText()+"' ");
                    while (c.moveToNext()){
                        index++;
                        id=c.getInt(0);
                        name=c.getString(1);
                        email=c.getString(2);
                        pass=c.getString(3);
                        sdt = c.getString(4);
                    }

                    if(index>0) {
                        if(ckRemember.isChecked())
                        {
                            editor.putString("name",name);
                            editor.putInt("id",id);
                            editor.putString("email",email);
                            editor.putString("pass",pass);
                            editor.putString("phone",sdt);
                            editor.putBoolean("loggedIn",true);
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Đăng nhập tai khoan: "+name+" thành công!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                            Bundle bd = new Bundle();
//                            user user = new user(id,name,email,pass,sdt);
//                            bd.putSerializable("object_user",user);
//                            i.putExtras(bd);
                            startActivity(i);
                        }else
                        {
                            editor.putInt("id",id);
                            editor.putString("name",name);
                            editor.putString("email",email);
                            editor.putString("pass",pass);
                            editor.putString("phone",sdt);
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Đăng nhập tai khoan: "+name+" thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                            Bundle bd = new Bundle();
//                            user user = new user(id,name,email,pass,sdt);
//                            bd.putSerializable("object_user",user);
//                            i.putExtras(bd);
                            startActivity(i);
                        }


                    }else{
                        Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                        edt_email_login.setText("");
                        edt_pass_login.setText("");
                        edt_email_login.requestFocus();
                    }
                    index=0;
                }
            }
        });
        //
        txtToSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });
        //
        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Forgot.class));
            }
        });

    }
    //
    private void mapping()
    {
        btnLogin =findViewById(R.id.btnLogin);
        edt_email_login = findViewById(R.id.edtEmail_login);
        edt_pass_login = findViewById(R.id.edtPass_login);
        txtToSingup = findViewById(R.id.txtToSingup);
        txtForgot = findViewById(R.id.txtForgot);
        ckRemember = findViewById(R.id.ckRemember);
    }
    Boolean ktr(){
        boolean a;
        String ktr_mail=edt_email_login.getText().toString();
        String ktr_pass=edt_pass_login.getText().toString();
        if(ktr_mail.equals("")){
            a=true;
        }else if(ktr_pass.equals("")){
            a=true;
        }else{
            a=false;
        }
        return a;
    }
}