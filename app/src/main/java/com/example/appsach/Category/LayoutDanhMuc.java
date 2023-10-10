package com.example.appsach.Category;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appsach.R;

import java.util.ArrayList;

import SQLite.BitmapUtils;
import adapter.Son.DanhMucAdapter;
import adapter.Son.ItemAdapter;
import adapter.Son.RecyclerItemClickListener;
import model.Son.DanhMuc;
import model.Son.Item;

public class LayoutDanhMuc extends Activity {
    private RecyclerView recyclerView_item;
    private ListView listView_DanhMuc;
    private ItemAdapter adapter_item;
    private ArrayList<DanhMuc> arrDanhMuc;
    EditText ed_Search;
    Cursor cursor;
    SQLiteDatabase db;
    ArrayList<Item> arr;
    ImageView img_searching;
    private DanhMucAdapter danhMucAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danh_muc);
        db = SQLiteDatabase.openOrCreateDatabase("/data/data/com.application.test_baitaplon/databases/temp.db", null);
        anhxa();

        //danh muc
        danhMucAdapter = new DanhMucAdapter(this, R.layout.layout_custom_danhmuc, arrDanhMuc);
        getDataToListViewDanhMuc();
        listView_DanhMuc.setAdapter(danhMucAdapter);

        //recycleView
        adapter_item = new ItemAdapter(LayoutDanhMuc.this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        //insertSubData();
        getListData();
        recyclerView_item.requestFocus();
        recyclerView_item.setLayoutManager(gridLayoutManager);

        adapter_item.setData(arr);
        recyclerView_item.setAdapter(adapter_item);

        //ed_search
        searching();


        //setOnItemClickListener for RecycleView
        recyclerView_item.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView_item, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView ten = view.findViewById(R.id.tv_name);
                Intent intent = new Intent(LayoutDanhMuc.this, LayoutInfoItem.class);
                String name = ten.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putInt("idlayout",R.layout.layout_danh_muc);
                intent.putExtra("name_item", bundle);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        ////setOnItemClickListener for ListView
        listView_DanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView danhMuc = view.findViewById(R.id.tv_danhmuc);
                String search = danhMuc.getText().toString();
                arr.clear();
                updateRecycleViewUpToDanhMuc(search);
                adapter_item.setData(arr);
                Toast.makeText(LayoutDanhMuc.this, search, Toast.LENGTH_LONG).show();
            }
        });

        //insert data to table danhmuc, tac_gia, nha xuat ban, nha phat hanh
    }

    private void searching() {
        img_searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ed_Search.getText().toString();
                finalSeaching(s);
            }
        });

        ed_Search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
                    return false;
                if (i == KeyEvent.KEYCODE_ENTER) {
                    String key_word = ed_Search.getText().toString();
                    finalSeaching(key_word);
                    return true;
                }
                return false;
            }
        });
    }

    private void finalSeaching(String s) {
        Intent intent = new Intent(LayoutDanhMuc.this, LayoutTimKiem.class);
        Bundle bundle = new Bundle();
        bundle.putString("key_word", s);
        intent.putExtra("key", bundle);
        ed_Search.setText("");
        startActivity(intent);
    }

    private void anhxa() {
        arrDanhMuc = new ArrayList<>();
        ed_Search = findViewById(R.id.ed_timKiem1);
        listView_DanhMuc = findViewById(R.id.lv_danh_muc);
        recyclerView_item = findViewById(R.id.recyclerV_Item);
        img_searching = findViewById(R.id.img_searching1);
    }

    @SuppressLint("Range")
    private void getDataToListViewDanhMuc() {
        cursor = db.rawQuery("select * from danh_muc", null);
        while (cursor.moveToNext()) {
            arrDanhMuc.add(new DanhMuc(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_danhmuc"))), cursor.getString(cursor.getColumnIndex("ten_danhmuc"))));
        }
        danhMucAdapter.notifyDataSetChanged();
    }

    @SuppressLint("Range")
    private void updateRecycleViewUpToDanhMuc(String s) {
        arr = new ArrayList<Item>();
        cursor = db.rawQuery("select * from book " +
                "join danh_muc on danh_muc.id_danhmuc = book.id_danhmuc " +
                "where danh_muc.ten_danhmuc = '" + s + "';", null);
        while (cursor.moveToNext()) {
            byte[] temp = cursor.getBlob(6);
            arr.add(new Item(cursor.getString(1), BitmapUtils.getImage(temp)));
        }
    }

    private void getListData() {
        arr = new ArrayList<Item>();
        cursor = db.rawQuery("select * from book", null);
        while (cursor.moveToNext()) {
            byte[] temp = cursor.getBlob(6);
            arr.add(new Item(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(9)), BitmapUtils.getImage(temp)));
        }
    }

    /*
    First News
    Nhã Nam
    Kim Đồng
    Alpha Books
    Trẻ
    Văn Lang
    NXB Tổng Hợp TP. HCM

     */

    private void insertSubData() {
        String c = "delete from book where tieude = 'Tư duy nhanh và chậm'";
        db.execSQL(c);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sach6);
        byte[] bytes = BitmapUtils.getByte(bitmap);

        ContentValues values = new ContentValues();

        values.put("tieude","Tư duy nhanh và chậm");
        values.put("gia ",189000);
        values.put("id_tacgia ",5);
        values.put("id_danhmuc ",3);
        values.put("tomtat","Tư Duy Nhanh Và Chậm\n" +
                "\n" +
                "Chúng ta thường tự cho rằng con người là sinh vật có lý trí mạnh mẽ, khi quyết định hay đánh giá vấn đề luôn kĩ lưỡng và lý tính.\n" +
                "\n" +
                "Nhưng sự thật là, dù bạn có cẩn trọng tới mức nào, thì trong cuộc sống hàng ngày hay trong vấn đề liên quan đến kinh tế, bạn vẫn có những quyết định dựa trên cảm tính chủ quan của mình. “Tư duy nhanh và chậm”, cuốn sách nổi tiếng tổng hợp tất cả nghiên cứu được tiến hành qua nhiều thập kỷ của nhà tâm lý học từng đạt giải Nobel Kinh tế Daniel Kahneman sẽ cho bạn thấy những sư hợp lý và phi lý trong tư duy của chính bạn.\n" +
                "\n" +
                "Cuốn sách được đánh giá là “kiệt tác” trong việc thay đổi hành vi của con người, Tư duy nhanh và chậm đã dành được vô số giải thưởng danh giá, lọt vào Top 11 cuốn sách kinh doanh hấp dẫn nhất.\n" +
                "\n" +
                "Đã có rất nhiều cuốn sách nói về tính hợp lý và phi lý của con người trong tư duy, trong việc đánh giá và ra quyết định, nhưng Tư duy nhanh và chậm được Tạp chí Tài chính Mỹ đánh giá là “kiệt tác”.\n" +
                "\n" +
                "Bạn nghĩ rằng bạn tư duy nhanh, hay chậm? Bạn tư duy và suy nghĩ theo lối “trông mặt bắt hình dong”, đánh giá mọi vật nhanh chóng bằng cảm quan, quyết định dựa theo cảm xúc hay tư duy một cách cẩn thận, chậm rãi nhưng logic hợp lý về một vấn đề. Tư duy nhanh và chậm sẽ đưa ra và lý giải hai hệ thống tư duy tác động đến con đường nhận thức của bạn.\n" +
                "\n" +
                "Kahneman gọi đó là: hệ thống 1 và hệ thống 2. Hệ thống 1, còn gọi là cơ chế nghĩ nhanh, tự động, thường xuyên được sử dụng, cảm tính, rập khuôn và tiềm thức. Hệ thống 2, còn gọi là cơ chế nghĩ chậm, đòi hỏi ít nỗ lực, ít được sử dụng, dùng logic có tính toán và ý thức.\n" +
                "\n" +
                "Trong một loạt thí nghiệm tâm lý mang tính tiên phong, Kahneman và Tversky chứng minh rằng, con người chúng ta thường đi đến quyết định theo cơ chế nghĩ nhanh hơn là ghĩ chậm. Phần lớn nội dung của cuốn sách chỉ ra những sai lầm của con người khi suy nghĩ theo hệ thống 1.\n" +
                "\n" +
                "Kahneman chứng minh rằng chúng ta tệ hơn những gì chúng ta tưởng: đó là chúng ta không biết những gì chúng ta không biết! ");
        values.put("hinhanh",bytes);
        values.put("number_of_pages",610);
        values.put("id_nhaxb",1);
        values.put("id_nhaph",4);
        db.insert("book",null,values);

        cursor = db.rawQuery("select * from book", null);
        Toast.makeText(this, cursor.getCount() + "", Toast.LENGTH_LONG).show();
    }
}