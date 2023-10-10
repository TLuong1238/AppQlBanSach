package com.example.profilecart.activity;

import static java.lang.Math.random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.profilecart.R;
import com.example.profilecart.adapter.GiohangAdapter;
import com.example.profilecart.model.Sanpham;
import com.example.profilecart.model.User;
import com.example.profilecart.sqlite;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class GioHang extends Activity {
    ListView lvGiohang;
    TextView txtThongbao;
    static TextView txtTongtien;
    Button btnThanhtoan, btnTieptucmuahang, btnXoatatca;
    GiohangAdapter giohangAdapter;
    int dem=0;
    public static ArrayList<Sanpham> lstGiohang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        Anhxa();
        lstGiohang.add(new Sanpham(1, "iphone", 10000, "abc", 1));
        lstGiohang.add(new Sanpham(1, "samsung", 20000, "asd", 1));
        lstGiohang.add(new Sanpham(1, "oppo", 15000, "qwe", 1));
        giohangAdapter = new GiohangAdapter(GioHang.this, lstGiohang);
        lvGiohang.setAdapter(giohangAdapter);
        XoaItem();
        Xoatatca();
        Thanhtoan();
        Tongtien();
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
//                          Bundle bundle = getIntent().getExtras();
//                          User user = bundle.get();
//                          int id_tk = user.getId_taikhoan();
                            int id = 10;
                            sqlite db = new sqlite(GioHang.this, "cart.db", null, 1);
                            db.QueryData(("CREATE TABLE IF NOT EXISTS tbl_giohang(id_giohang INTEGER PRIMARY KEY AUTOINCREMENT, id_taikhoan INTEGER, ma_donhang INTEGER)"));
                            db.QueryData("INSERT INTO tbl_giohang VALUES(null, '" + id + "','" + ma_donhang + "');");
                            db.QueryData(("CREATE TABLE IF NOT EXISTS tbl_chitietdonhang(id_chitietdonhang INTEGER PRIMARY KEY AUTOINCREMENT, ma_donhang INTEGER, id_taikhoan INTEGER, tensanpham TEXT, soluong INTEGER, gia INTEGER)"));
                            for (Sanpham item: lstGiohang) {
                                db.QueryData("INSERT INTO tbl_chitietdonhang VALUES(null, '" + ma_donhang + "', '" + id + "', '" + item.getTen_sanpham() + "', '" + item.getSoluong() + "', '" + item.getGiasp() + "');");
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
        txtTongtien.setText(decimalFormat.format(tongtien)+ " vnd");
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
    }
}
