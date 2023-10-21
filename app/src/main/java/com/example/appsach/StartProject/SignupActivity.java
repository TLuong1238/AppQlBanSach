package com.example.appsach.StartProject;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appsach.R;
import SQLite.sqlite;

public class SignupActivity extends AppCompatActivity {

    private Button btnSignup;
    private TextView txtLogin;
    private EditText edt_name_signup,edt_email_signup,edt_pass_signup,edt_repass;
    int index= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mapping();
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder db = new AlertDialog.Builder(SignupActivity.this);
                if(ktr()==true){

                    db.setMessage("Yêu cầu điền đầy đủ dữ liệu");
                    db.setTitle("Thông báo");
                    db.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            xoatr();
                        }
                    });
                }else{
                    sqlite s = new sqlite(SignupActivity.this, R.string.databaseName+"", null, 1);
                    s.QueryData("CREATE TABLE IF NOT EXISTS user( id INTEGER  PRIMARY KEY AUTOINCREMENT,name TEXT,email EMAIL,password TEXT,phone Text null)");
                    s.createTable();
                    s.updateDB();
                    Cursor c = s.getData("SELECT * FROM user WHERE email ='"+edt_email_signup.getText()+"'OR name ='"+edt_name_signup.getText()+"'");
                    while (c.moveToNext()){
                        index++;
                    }
                    if(index>0){
                        db.setTitle("Thông báo");
                        db.setMessage("Tài khoản đã tồn tại");
                        db.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                xoatr();
                            }
                        });

                    }else {
                        String pass = edt_pass_signup.getText().toString();
                        String firm = edt_repass.getText().toString();
                        if(pass.equals(firm)==false){
                            Toast.makeText(SignupActivity.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                            edt_pass_signup.requestFocus();
                            edt_pass_signup.setText("");
                            edt_repass.setText("");
                        }else{
                            s.QueryData("INSERT INTO user VALUES (null,'"+edt_name_signup.getText()+"','"+edt_email_signup.getText()+"','"+edt_pass_signup.getText()+"',null)" );
                            Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignupActivity.this,LoginActivity.class);
                            startActivity(i);
                            s.close();
                            finish();
                        }
                    }
                    index=0;
                }
                db.show();

            }
        });

    }

    private void mapping()
    {
        btnSignup =findViewById(R.id.btnSignUp);
        edt_name_signup = findViewById(R.id.edtName_signup);
        edt_email_signup = findViewById(R.id.edtEmail_signup);
        edt_pass_signup = findViewById(R.id.edtPass_signup);
        edt_repass = findViewById(R.id.edtRepass);
        txtLogin = findViewById(R.id.txtLogin);
    }
    private void xoatr(){
        edt_name_signup.setText("");
        edt_pass_signup.setText("");
        edt_email_signup.setText("");
        edt_repass.setText("");
    }
    Boolean ktr(){
        boolean a ;

        String ktr_ten = edt_name_signup.getText().toString();
        String ktra_pass = edt_pass_signup.getText().toString();
        String ktr_xt = edt_repass.getText().toString();
        String ktr_email = edt_email_signup.getText().toString();
        if(ktr_ten.equals("")){
            a=true;

        }else if(ktra_pass.equals("")){

            a=true;
        }else if (ktr_xt.equals("")){

            a=true;
        }else if(ktr_email.equals("")){

            a=true;
        }else{
            a = false;
        }
        return a;

    }
}