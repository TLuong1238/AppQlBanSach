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
import com.example.appsach.R;
import com.example.appsach.admin.MainAdmin;

import SQLite.sqlite;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText edt_email_login,edt_pass_login;
    private TextView txtToSingup,txtForgot;
    private String name,email,pass,sdt,nameAdmin,passAdmin,emailAdmin;
    private int id;
    int index = 0,count = 0;
    private CheckBox ckRemember;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private Boolean logged;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        //
        edt_email_login.setText("");
        edt_pass_login.setText("");
        ckRemember.setChecked(false);
        //
        sp = getSharedPreferences("LoginData",MODE_PRIVATE);
        editor = sp.edit();
        boolean login = sp.getBoolean("loggedIn",false);
        String ten = sp.getString("nameAdmin","");
        if(login && !ten.equals("admin"))
        {
            Toast.makeText(this, "Chào mừng: "+sp.getString("name","")+" quay trở lại!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        } else if (login && ten.equals("admin")){
            Toast.makeText(this, "Chào mừng: "+sp.getString("nameAdmin","")+" quay trở lại!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainAdmin.class));
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
                    //s.QueryData("CREATE TABLE IF NOT EXISTS admin(name TEXT,email EMAIL,password TEXT)");
//                    String a1 = "admin";
//                    String a2 = "admin@gmail.com";
//                    String a3 = "1";
//                    s.QueryData("INSERT INTO admin VALUES ('"+a1+"','"+a2+"','"+a3+"')");
                    Cursor c = s.getData("SELECT * From user WHERE email ='"+edt_email_login.getText()+"' AND password ='"+edt_pass_login.getText()+"' ");
                    Cursor c2 = s.getData("SELECT * From admin WHERE email ='"+edt_email_login.getText()+"' AND password ='"+edt_pass_login.getText()+"' ");
                    while (c.moveToNext())
                    {
                        index++;
                        id=c.getInt(0);
                        name=c.getString(1);
                        email=c.getString(2);
                        pass=c.getString(3);
                        sdt = c.getString(4);
                    }
                    while (c2.moveToNext())
                    {
                     count++;
                     nameAdmin = c2.getString(0);
                     passAdmin = c2.getString(2);
                     emailAdmin= c2.getString(1);
                    }

                    if(count>0 )
                    {
                        logged = ckRemember.isChecked();
                        //
                        editor.putBoolean("loggedIn",logged);
                        editor.putString("nameAdmin",nameAdmin);
                        editor.putString("passAdmin",passAdmin);
                        editor.putString("emailAdmin",emailAdmin);
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Đăng nhập tai khoan: "+nameAdmin+" thành công!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainAdmin.class);
                        startActivity(i);

                    }else if(index>0) {
                        logged = ckRemember.isChecked();
                        //

                        editor.putString("name",name);
                        editor.putInt("id",id);
                        editor.putString("email",email);
                        editor.putString("pass",pass);
                        editor.putString("phone",sdt);
                        editor.putBoolean("loggedIn",logged);
                        editor.apply();

                        Toast.makeText(LoginActivity.this, "Đăng nhập tai khoan: "+name+" thành công!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);

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