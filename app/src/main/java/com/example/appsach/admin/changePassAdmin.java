package com.example.appsach.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appsach.R;
import com.example.appsach.StartProject.LoginActivity;
import SQLite.sqlite;

public class changePassAdmin extends AppCompatActivity {
    private EditText edtNewPass, edtConfirmPass;
    private Button btnXacNhanChange;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_mk);
        mapping();
        Bundle b = getIntent().getExtras();
        modelAdmin admin = (modelAdmin) b.get("objectAdmin");
        btnXacNhanChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNewPass.getText().equals("") || edtConfirmPass.getText().equals(""))
                {
                    Toast.makeText(changePassAdmin.this, "Yêu cầu điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    edtNewPass.setText("");
                    edtConfirmPass.setText("");
                    edtNewPass.requestFocus();
                }else
                {
                    String pass = edtNewPass.getText().toString();
                    String firm = edtConfirmPass.getText().toString();
                    if (!pass.equals(firm)) {
                        Toast.makeText(changePassAdmin.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                        edtNewPass.setText("");
                        edtConfirmPass.setText("");
                        edtNewPass.requestFocus();
                    } else {
                        sqlite s = new sqlite(changePassAdmin.this, R.string.databaseName+"", null, 1);
                        s.QueryData("UPDATE admin SET password = '" + edtNewPass.getText() + "' WHERE email ='" + admin.getEmailAdmin().toString().trim() + "'AND name ='" + admin.getNameAdmin().toString().trim() + "'");
                        Toast.makeText(changePassAdmin.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(changePassAdmin.this, LoginActivity.class);
                        startActivity(i);
                        s.close();
                        finish();
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
