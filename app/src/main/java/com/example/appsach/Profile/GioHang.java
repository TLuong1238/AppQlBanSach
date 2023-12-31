package com.example.appsach.Profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.appsach.Home.MainActivity;
import com.example.appsach.R;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.Vanh.GiohangAdapter;
import model.Vanh.ItemGiohang;
import model.user;

public class GioHang extends Activity {
    ListView lvGiohang;
    TextView txtThongbao;
    static TextView txtTongtien;
    Button btnThanhtoan, btnTieptucmuahang, btnXoatatca;
    GiohangAdapter giohangAdapter;
    SharedPreferences sp;
    public static ArrayList<ItemGiohang> lstGiohang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        Anhxa();
        AddGiohang();

        giohangAdapter = new GiohangAdapter(GioHang.this, lstGiohang);
        lvGiohang.setAdapter(giohangAdapter);
        XoaItem();
        Xoatatca();
        Thanhtoan();
        Tongtien();
        btnTieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioHang.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @SuppressLint("Range")
    private void AddGiohang() {
        user user = new user(sp.getInt("id",0),sp.getString("name",""),sp.getString("email",""),
                sp.getString("pass",""),sp.getString("phone",""));
        int id_tk = user.getId_user();
        sqlite s = new sqlite(GioHang.this,R.string.databaseName+"",null,1);
        Cursor cursor = s.getData("SELECT * FROM gio_hang WHERE id_taikhoan = '" + id_tk + "'");

        while (cursor.moveToNext()){
            byte[] temp = cursor.getBlob(cursor.getColumnIndex("hinhanh"));
            lstGiohang.add(new ItemGiohang(cursor.getInt(cursor.getColumnIndex("id_sach")),
                    cursor.getInt(cursor.getColumnIndex("id_taikhoan")),
                    cursor.getString(cursor.getColumnIndex("tensach")), cursor.getLong(cursor.getColumnIndex("gia")),
                    BitmapUtils.getImage(temp), cursor.getInt(cursor.getColumnIndex("soluong"))));
        }
        giohangAdapter = new GiohangAdapter(GioHang.this, lstGiohang);
        lvGiohang.setAdapter(giohangAdapter);
        giohangAdapter.notifyDataSetChanged();
    }

    private void Thanhtoan() {
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHang.this);
                builder.setTitle("Xác nhận đơn hàng");

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_thanhtoan, null);
                EditText edtTen = dialogView.findViewById(R.id.edtTen);
                EditText edtDiachi = dialogView.findViewById(R.id.edtDiachi);
                EditText edtSDT = dialogView.findViewById(R.id.edtSDT);
                builder.setView(dialogView);

                builder.setPositiveButton("Thanh toán đơn hàng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(edtTen.getText().toString().isEmpty() || edtDiachi.getText().toString().isEmpty() || edtSDT.getText().toString().isEmpty()){
                            Toast.makeText(GioHang.this,"Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Random random = new Random();
                            int ma_donhang = random.nextInt(1000 - 10) + 10;
                            user user = new user(sp.getInt("id",0),sp.getString("name",""),sp.getString("email",""),
                                    sp.getString("pass",""),sp.getString("phone",""));
                            int id_tk = user.getId_user();
                            sqlite db = new sqlite(GioHang.this, R.string.databaseName+"", null, 1);
                            db.QueryData(("CREATE TABLE IF NOT EXISTS tbl_hoadon(id_hoadon INTEGER PRIMARY KEY AUTOINCREMENT, id_taikhoan INTEGER, ma_donhang INTEGER, tinhtrang INTEGER)"));
                            db.QueryData("INSERT INTO tbl_hoadon VALUES(null, '" + id_tk + "','" + ma_donhang + "', '" + 1 + "');");
                            db.QueryData(("CREATE TABLE IF NOT EXISTS tbl_chitietdonhang(id_chitietdonhang INTEGER PRIMARY KEY AUTOINCREMENT, ma_donhang INTEGER, id_taikhoan INTEGER, id_sanpham INTEGER, tensanpham TEXT, soluong INTEGER, gia INTEGER)"));
                            for (ItemGiohang item: lstGiohang) {
                                db.QueryData("INSERT INTO tbl_chitietdonhang VALUES(null, '" + ma_donhang + "', '" + id_tk + "', '" + item.getId_sanpham() + "', '" + item.getTen_sanpham() + "', '" + item.getSoluong() + "', '" + item.getGiasp() + "');");
                                db.QueryData("DELETE FROM gio_hang");
                            }
                            lstGiohang.clear();
                            giohangAdapter.notifyDataSetChanged();
                            Tongtien();
                            txtThongbao.setVisibility(View.VISIBLE);
                        }
                    }
                });

                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Tongtien();
                    }
                });
                builder.show();
            }
        });
    }

    private void Xoatatca() {
        btnXoatatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHang.this);
                builder.setTitle("Xác nhận xoá toàn bộ sản phẩm");
                builder.setMessage("Bạn có chắc chắn xoá toàn bộ sản phẩm khỏi giỏ hàng");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(lstGiohang.size()<=0){
                            txtThongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            sqlite db = new sqlite(GioHang.this, R.string.databaseName+"", null, 1);
                            db.QueryData("DELETE FROM gio_hang");
                            lstGiohang.clear();
                            giohangAdapter.notifyDataSetChanged();
                            Tongtien();
                            txtThongbao.setVisibility(View.VISIBLE);
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        Tongtien();
                    }
                });
                builder.show();
            }
        });
    }

    private void XoaItem() {
        lvGiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHang.this);
                builder.setTitle("Xác nhận xoá sản  phẩm");
                builder.setMessage("Bạn có chắc chắn muốn xoá sản phẩm khỏi giỏ hàng");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(lstGiohang.size()<=0){
                            txtThongbao.setVisibility(View.VISIBLE);
                        }
                        else{
                            int id = lstGiohang.get(position).getId_sanpham();
                            sqlite db = new sqlite(GioHang.this, R.string.databaseName+"", null, 1);
                            db.QueryData("DELETE FROM gio_hang WHERE id_sach = '"+ id +"'");
                            lstGiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            Tongtien();
                            if(lstGiohang.size()<=0){
                                txtThongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                txtThongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                Tongtien();
                            }
                        }
                    }
                });

                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        Tongtien();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void Tongtien() {
        long tongtien = 0;
        for(int i=0; i<lstGiohang.size(); i++){
            tongtien += lstGiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongtien.setText(decimalFormat.format(tongtien)+ " VND");
    }

    private void Anhxa() {
        lstGiohang = new ArrayList();
        lvGiohang = findViewById(R.id.lvGiohang);
        txtThongbao = findViewById(R.id.txtThongbao);
        txtThongbao.setVisibility(View.INVISIBLE);
        txtTongtien = findViewById(R.id.txtTongtien);
        btnThanhtoan = findViewById(R.id.btnThanhtoan);
        btnTieptucmuahang = findViewById(R.id.btnTieptucmuahang);
        btnXoatatca = findViewById(R.id.btnXoatatca);
        sp = getSharedPreferences("LoginData",MODE_PRIVATE);
    }
}
