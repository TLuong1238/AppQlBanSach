package com.example.appsach.StartProject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsach.R;

public class Welcom extends AppCompatActivity {

    private TextView txtLogin,txtSignup;
    private ImageView imgLogin,imgSignup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom_layout);
        mapping();
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcom.this, LoginActivity.class));
                finish();
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcom.this, LoginActivity.class));
                finish();
            }
        });
        imgSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcom.this, SignupActivity.class));
                finish();
            }
        });
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcom.this, SignupActivity.class));
                finish();
            }
        });
    }

    private void mapping()
    {
        txtLogin = findViewById(R.id.txtLogin);
        txtSignup =findViewById(R.id.txtSignup);
        imgLogin = findViewById(R.id.imgLogin);
        imgSignup = findViewById(R.id.imgSignup);
    }
}
