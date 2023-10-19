package com.example.appsach.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appsach.R;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.Son.AdaterSpinner;
import model.Son.SubDataItem;

public class AdminXulyBook extends Activity {

    Button btn1, btn2, chonAnh, chonNgay;

    EditText tieude, soTrang, gia, luotmua, tomtat;

    TextView date;

    Spinner spDanhmuc, spTacgia, spNhaXB, spNhaPh;

    ImageView hinhanh;

    AdaterSpinner adaterDanhmuc, adapterAuthor, adapterNhaPh, adapterNhaXB;

    ArrayList<SubDataItem> subListDm, subListTg, subListNhaph, subListNhaXb;

    sqlite database;

    Intent intent;
    Bundle bundle;
    int SELECT_PICTURE = 200;
    private int mYear, mMonth, mDay, check, id;

    String selected, spinner_item;
    SubDataItem danhMuc, tacGia, nhaXb, nhaPh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_execute_book);
        database = new sqlite(this, "test3.db", null, 1);
        anhxa();
        subListDm = new ArrayList<>();
        subListNhaph = new ArrayList<>();
        subListTg = new ArrayList<>();
        subListNhaXb = new ArrayList<>();

        createSpinner(spDanhmuc, adaterDanhmuc, subListDm, "danh_muc");
        createSpinner(spTacgia, adapterAuthor, subListTg, "tac_gia");
        createSpinner(spNhaPh, adapterNhaPh, subListNhaph, "nha_phat_hanh");
        createSpinner(spNhaXB, adapterNhaXB, subListNhaXb, "nha_xuatban");


        spDanhmuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                danhMuc = new SubDataItem(subListDm.get(i).getId(), subListDm.get(i).getName());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spTacgia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tacGia = new SubDataItem(subListDm.get(i).getId(), subListTg.get(i).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spNhaPh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nhaPh = new SubDataItem(subListDm.get(i).getId(), subListNhaph.get(i).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spNhaXB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nhaXb = new SubDataItem(subListDm.get(i).getId(), subListNhaXb.get(i).getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        chonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminXulyBook.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        chonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageChoser();
            }
        });


        intent = getIntent();
        bundle = intent.getBundleExtra("pack");
        String request = bundle.getString("request");
        if (request.equals("adding")) {
            adding();
        } else if (request.equals("changeInfo")) {
            changeInfo();
        }
    }

    private void adding() {
        btn1.setText("Trở về");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminXulyBook.this, AdminBookList.class);
                startActivity(i);
            }
        });
        btn2.setText("Hoàn tất thêm");
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                SQLiteDatabase db = database.getWritableDatabase();

                Bitmap bitmap = ((BitmapDrawable) hinhanh.getDrawable()).getBitmap();
                byte[] img = BitmapUtils.getByte(bitmap);

                Cursor c = database.getData("select * from book");
                check = c.getCount();

                contentValues.put("tieude", tieude.getText().toString());
                contentValues.put("gia ", gia.getText().toString());
                contentValues.put("id_tacgia ", tacGia.getId());
                contentValues.put("id_danhmuc ", danhMuc.getId());
                contentValues.put("tomtat", tomtat.getText().toString());
                contentValues.put("hinhanh", img);
                contentValues.put("number_of_pages", soTrang.getText().toString());
                contentValues.put("ngay_xuatban", date.getText().toString());
                contentValues.put("id_nhaxb", nhaXb.getId());
                contentValues.put("id_nhaph", nhaPh.getId());
                contentValues.put("luotmua", luotmua.getText().toString());
                db.insert("book", null, contentValues);

                c = database.getData("select * from book");
                if (check == c.getCount() - 1) {
                    Toast.makeText(AdminXulyBook.this, "Đã thêm thành công", Toast.LENGTH_LONG).show();
                }
                tieude.setText("");
                soTrang.setText("");
                gia.setText("");
                luotmua.setText("");
                tomtat.setText("");
                tieude.requestFocus();
                c.close();
            }
        });
    }

    @SuppressLint("Range")
    private void changeInfo() {
        id = bundle.getInt("id");
        Cursor c = database.getData("select * from book where id_book = '" + id + "'");
        c.moveToFirst();
        Cursor c1 = database.getData("select * from danh_muc where id_danhmuc = '" + c.getString(c.getColumnIndex("id_danhmuc")) + "'");
        Cursor c2 = database.getData("select * from tac_gia where id_tacgia = '" + c.getString(c.getColumnIndex("id_tacgia")) + "'");
        Cursor c3 = database.getData("select * from nha_xuatban where id_nhaxb = '" + c.getString(c.getColumnIndex("id_nhaxb")) + "'");
        Cursor c4 = database.getData("select * from nha_phat_hanh where id_nhaph = '" + c.getString(c.getColumnIndex("id_nhaph")) + "'");


        c1.moveToFirst();
        c2.moveToFirst();
        c3.moveToFirst();
        c4.moveToFirst();
        tieude.setText(c.getString(c.getColumnIndex("tieude")));
        tieude.setEnabled(false);
        soTrang.setText(c.getString(c.getColumnIndex("number_of_pages")));
        soTrang.setEnabled(false);
        gia.setText(c.getString(c.getColumnIndex("gia")));
        gia.setFocusable(false);
        luotmua.setText(c.getString(c.getColumnIndex("luotmua")));
        luotmua.setEnabled(false);
        tomtat.setText(c.getString(c.getColumnIndex("tomtat")));
        tomtat.setEnabled(false);
        spNhaXB.setPrompt(c3.getString(c3.getColumnIndex("ten_nhaxb")));
        spNhaXB.setEnabled(false);
        spDanhmuc.setPrompt(c1.getString(c1.getColumnIndex("ten_danhmuc")));
        spDanhmuc.setEnabled(false);
        spNhaPh.setPrompt(c4.getString(c4.getColumnIndex("ten_nhaph")));
        spNhaPh.setEnabled(false);
        spTacgia.setPrompt(c2.getString(c2.getColumnIndex("ten_tacgia")));
        spTacgia.setEnabled(false);
        byte[] img = c.getBlob(c.getColumnIndex("hinhanh"));
        hinhanh.setImageBitmap(BitmapUtils.getImage(img));
        chonNgay.setFocusable(false);
        chonAnh.setFocusable(false);
        date.setText(c.getString(c.getColumnIndex("ngay_xuatban")));
        c.close();
        c1.close();
        c2.close();
        c3.close();
        c4.close();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tieude.setEnabled(true);
                soTrang.setEnabled(true);
                gia.setEnabled(true);
                luotmua.setEnabled(true);
                tomtat.setEnabled(true);
                spNhaXB.setEnabled(true);
                spDanhmuc.setEnabled(true);
                spNhaPh.setEnabled(true);
                spTacgia.setEnabled(true);
                chonNgay.setFocusable(true);
                chonAnh.setFocusable(true);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable) hinhanh.getDrawable()).getBitmap();
                byte[] img = BitmapUtils.getByte(bitmap);
                database.QueryData("UPDATE `book` SET `tieude`='" + tieude.getText().toString() + "'," +
                        "`gia`='" + gia.getText().toString() + "',`id_danhmuc`='" + danhMuc.getId() + "',`id_tacgia`='" + tacGia.getId() + "'," +
                        "`id_nhaph`='" + nhaPh.getId() + "',`id_nhaxb`='" + nhaXb.getId() + "',`number_of_pages`='" + soTrang.getText().toString() + "'," +
                        "`ngay_xuatban`='" + date.getText() + "',`tomtat`='" + tomtat.getText().toString() + "'," +
                        "`luotmua`='" + luotmua.getText().toString() + "' WHERE id_book = '" + id + "'");
                SQLiteDatabase db = database.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("hinhanh",img);
                db.update("images", contentValues, "id = ?", new String[]{Integer.toString(id)});
                db.close();
                Intent i = new Intent(AdminXulyBook.this, DanhMucAdmin.class);
                startActivity(i);
            }
        });
    }

    private void anhxa() {
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        chonAnh = findViewById(R.id.adB_ImageChooser);
        chonNgay = findViewById(R.id.adB_btnDatePicker);
        tieude = findViewById(R.id.adB_edTitle);
        soTrang = findViewById(R.id.adB_edNumber);
        gia = findViewById(R.id.adB_edPrice);
        luotmua = findViewById(R.id.adB_edLuotMua);
        tomtat = findViewById(R.id.adB_edTomtat);
        date = findViewById(R.id.adB_tvNgayXB);
        spDanhmuc = findViewById(R.id.adB_spCategory);
        spTacgia = findViewById(R.id.adB_spAuthor);
        spNhaXB = findViewById(R.id.adB_spNhaXB);
        spNhaPh = findViewById(R.id.adB_spNhaph);
        hinhanh = findViewById(R.id.adB_Image);
    }

    private void createSpinner(Spinner spinner, AdaterSpinner adapter, ArrayList<SubDataItem> arr, String table) {
        adapter = new AdaterSpinner(AdminXulyBook.this, R.layout.custom_spinner, arr);
        spinner.setAdapter(adapter);
        if (table.equals("danh_muc")) {
            getDataForArrSpinner(arr, table, "ten_danhmuc", "id_danhmuc");
        } else if (table.equals("tac_gia")) {
            getDataForArrSpinner(arr, table, "ten_tacgia", "id_tacgia");
        } else if (table.equals("nha_xuatban")) {
            getDataForArrSpinner(arr, table, "ten_nhaxb", "id_nhaxb");
        } else if (table.equals("nha_phat_hanh")) {
            getDataForArrSpinner(arr, table, "ten_nhaph", "id_nhaph");
        }
        adapter.notifyDataSetChanged();

    }

    @SuppressLint("Range")
    private void getDataForArrSpinner(ArrayList<SubDataItem> arr, String table, String name, String id) {
        Cursor c = database.getData("SELECT * FROM `" + table + "`");
        while (c.moveToNext()) {
            arr.add(new SubDataItem(c.getInt(c.getColumnIndex(id)), c.getString(c.getColumnIndex(name))));
        }
        c.close();
    }

    private void imageChoser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    hinhanh.setImageURI(selectedImageUri);
                }
            }
        }
    }

}
