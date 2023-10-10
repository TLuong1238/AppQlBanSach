package com.example.appsach.Home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appsach.R;

import model.Book;

public class BookDetail extends AppCompatActivity {
    ImageView imgDetail;
    TextView txtDetail;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail);
        txtDetail = findViewById(R.id.txtDetail);
        imgDetail = findViewById(R.id.imgDetail);
        Bundle bundle = getIntent().getExtras();
        Book book = (Book) bundle.get("objectBook");
        txtDetail.setText(book.getTitle());
        imgDetail.setImageResource(book.getSourceId());

    }
}
