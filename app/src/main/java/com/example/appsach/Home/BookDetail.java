//package com.example.appsach.Home;
//
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.appsach.R;
//
//import model.Book;
//
//public class BookDetail extends AppCompatActivity {
//    ImageView imgDetail;
//    TextView txtDetail;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.item_book);
//        txtDetail = findViewById(R.id.txtItem);
//        imgDetail = findViewById(R.id.imgBook);
//        Bundle bundle = getIntent().getExtras();
//        Book book = (Book) bundle.get("objectBook");
//        txtDetail.setText(book.getTitle());
//        imgDetail.setImageResource(book.getSourceId());
//
//    }
//}
