package com.example.appsach.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsach.R;
import com.example.appsach.StartProject.Forgot;
import com.example.appsach.StartProject.LoginActivity;
import com.example.appsach.admin.modelAdmin;

import SQLite.sqlite;
import model.user;

public class ChangePass extends AppCompatActivity {
    private EditText edtNewPass, edtConfirmPass;
    private Button btnXacNhanChange;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_mk);
        mapping();
        Bundle b = getIntent().getExtras();
        user getUser = (user) b.get("objectUser");


        btnXacNhanChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNewPass.getText().equals("") || edtConfirmPass.getText().equals(""))
                {
                    Toast.makeText(ChangePass.this, "Yêu cầu điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    edtNewPass.setText("");
                    edtConfirmPass.setText("");
                    edtNewPass.requestFocus();
                }else
                {
                    String pass = edtNewPass.getText().toString();
                    String firm = edtConfirmPass.getText().toString();
                    if (!pass.equals(firm)) {
                        Toast.makeText(ChangePass.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                        edtNewPass.setText("");
                        edtConfirmPass.setText("");
                        edtNewPass.requestFocus();
                    } else {
                        sqlite s = new sqlite(ChangePass.this, R.string.databaseName+"", null, 1);
                        s.QueryData("UPDATE user SET password = '" + edtNewPass.getText() + "' WHERE email ='" + getUser.getEmail_user().toString().trim() + "'AND name ='" + getUser.getName_user().toString().trim() + "'");
                        Toast.makeText(ChangePass.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ChangePass.this, LoginActivity.class);
                        startActivity(i);
                    }
                }
            }
        });
        }




    private void mapping()
    {
        edtNewPass = findViewById(R.id.edtNewpass);
        edtConfirmPass = findViewById(R.id.edtNewRepass);
        btnXacNhanChange = findViewById(R.id.btnXacNhaChange);
    }
}
