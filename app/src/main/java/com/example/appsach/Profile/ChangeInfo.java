package com.example.appsach.Profile;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsach.Home.MainActivity;
import com.example.appsach.R;

import SQLite.sqlite;
import model.user;

public class ChangeInfo extends AppCompatActivity {

    private user user , send;
    private EditText edtNewName,edtNewEmail,edtNewPhone;
    private Button btnChangeInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_info);

        Bundle bundle = getIntent().getExtras();
        user = (model.user) bundle.get("object_user");
        //
        mapping();
        edtNewName.setText(user.getName_user());
        edtNewEmail.setText(user.getEmail_user());
        edtNewPhone.setText(user.getSdt());
        //
        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNewName.getText().equals("") || edtNewEmail.getText().equals("") )
                {
                    edtNewName.setText("");
                    edtNewEmail.setText("");
                    edtNewName.requestFocus();
                    Toast.makeText(ChangeInfo.this, "Chưa điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else if(edtNewPhone.getText().equals(""))
                {
                    edtNewPhone.requestFocus();
                    Toast.makeText(ChangeInfo.this, "Chưa điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else if(edtNewPhone.getText().length() <=0 || edtNewPhone.getText().length() >11)
                {
                    edtNewPhone.setText("");
                    edtNewPhone.requestFocus();
                    Toast.makeText(ChangeInfo.this, "Số điện thoại không hợp lệ!", Toast.LENGTH_SHORT).show();
                } else {
                    sqlite s = new sqlite(ChangeInfo.this, R.string.databaseName+"", null, 1);
                    s.QueryData("UPDATE user SET name = '" + edtNewName.getText() + "', email = '"+edtNewEmail.getText()+"',phone = '"+edtNewPhone.getText()+"' " +
                            "WHERE id ='" + user.getId_user() + "'");
                    Cursor c = s.getData("SELECT * From user WHERE id ='" + user.getId_user() +"' ");
                    while(c.moveToNext())
                    {
                        send = new user(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));
                    }

                    Toast.makeText(ChangeInfo.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ChangeInfo.this, MainActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("object_user",send);
                    i.putExtras(bundle1);
                    startActivity(i);
                    }
            }
        });




    }

    private void mapping()
    {
        edtNewName = findViewById(R.id.edtNewName);
        edtNewEmail = findViewById(R.id.edtNewEmail);
        edtNewPhone = findViewById(R.id.edtNewPhone);
        btnChangeInfo =findViewById(R.id.btnChangeInfo);
    }

    private boolean kt()
    {


        return false;
    }

}
