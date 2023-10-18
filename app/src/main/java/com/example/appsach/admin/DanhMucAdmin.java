package com.example.appsach.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.appsach.R;

import java.util.ArrayList;

import SQLite.sqlite;
import adapter.Son.AdminArrayAdapter;
import model.Son.SubDataItem;

public class DanhMucAdmin extends Activity {
    Button btn_tacGia, btn_nhaXB, btn_nhaPH, btn_danhMuc, adding;

    TextView danhSach;

    ListView danhSach_subData;

    AdminArrayAdapter adminArrayAdapter;

    ArrayList<SubDataItem> arrayList;

    sqlite database;
    String table, id, name;

    private int check;

    @Override
    @SuppressLint("Range")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuc_admin);
        database = new sqlite(this, "test1.db", null, 1);

        anhxa();
        //default la tacgia
        table = "tac_gia";
        id = "id_tacgia";
        name = "ten_tacgia";
        arrayList = new ArrayList<>();
        Cursor cursor = database.getData("SELECT * FROM tac_gia");
        while (cursor.moveToNext()) {
            arrayList.add(new SubDataItem(cursor.getInt(cursor.getColumnIndex("id_tacgia")), cursor.getString(cursor.getColumnIndex("ten_tacgia"))));
        }
        adminArrayAdapter = new AdminArrayAdapter(this, R.layout.admin_array_custom, arrayList);
        danhSach_subData.setAdapter(adminArrayAdapter);
        adminArrayAdapter.notifyDataSetChanged();

        //button
        btn_tacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhSach.setText("Danh sách tác giả");
                table = "tac_gia";
                id = "id_tacgia";
                name = "ten_tacgia";
                updateListUpToTable(table);
            }
        });

        btn_danhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhSach.setText("Danh sách danh mục");
                table = "danh_muc";
                id = "id_danhmuc";
                name = "ten_danhmuc";
                updateListUpToTable(table);
            }
        });


        btn_nhaPH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhSach.setText("Danh sách nhà phát hành");
                table = "nha_phat_hanh";
                id = "id_nhaph";
                name = "ten_nhaph";
                updateListUpToTable(table);
            }
        });


        btn_nhaXB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhSach.setText("Danh sách nhà xuất bản");
                table = "nha_xuatban";
                id = "id_nhaxb";
                name = "ten_nhaxb";
                updateListUpToTable(table);
            }
        });

        //Thêm 1 trường mới trong danh sách sub data
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog builder = new AlertDialog.Builder(DanhMucAdmin.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_execute_button, null);
                builder.setView(dialogView);
                EditText ed_id = dialogView.findViewById(R.id.ed_idSubData);
                EditText ed_name = dialogView.findViewById(R.id.ed_nameSubData);
                Button btn_cancel = dialogView.findViewById(R.id.btn_cancelSub);
                Button btn_exeSub = dialogView.findViewById(R.id.btn_exeSubData);

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.cancel();
                    }
                });
                btn_exeSub.setText("Thêm");

                btn_exeSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor c = database.getData("SELECT * FROM `" + table + "`");
                        check = c.getCount();
                        String sqlInsert = "INSERT INTO `" + table + "`(`" + id + "`, `" + name + "`) " +
                                "VALUES ('" + ed_id.getText().toString() + "','" + ed_name.getText().toString() + "')";
                        database.QueryData(sqlInsert);
                        c = database.getData("SELECT * FROM `" + table + "`");
                        if (check == c.getCount() - 1) {
                            ed_id.setText("");
                            ed_name.setText("");
                            Toast.makeText(DanhMucAdmin.this, "Bạn đã thêm thành công", Toast.LENGTH_LONG).show();
                            updateListUpToTable(table);
                        } else {
                            Toast.makeText(DanhMucAdmin.this, "Câu lệnh truy vấn đang sai", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.show();
            }
        });

        //Sửa thông tin 1 trường trong subdata
        danhSach_subData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog builder = new AlertDialog.Builder(DanhMucAdmin.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_execute_button, null);
                builder.setView(dialogView);
                EditText ed_id = dialogView.findViewById(R.id.ed_idSubData);
                EditText ed_name = dialogView.findViewById(R.id.ed_nameSubData);
                Button btn_cancel = dialogView.findViewById(R.id.btn_cancelSub);
                Button btn_exeSub = dialogView.findViewById(R.id.btn_exeSubData);

                ed_id.setText(Integer.toString(arrayList.get(i).getId()));
                ed_name.setText(arrayList.get(i).getName());
                ed_id.setFocusable(false);
                ed_name.requestFocus();
                btn_exeSub.setText("Lưu thay đổi");

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder.cancel();
                    }
                });

                btn_exeSub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sqlUpdate = "UPDATE `"+table+"` SET `"+name+"`='"+ed_name.getText().toString()+"'" +
                                " WHERE `"+id+"`='"+ed_id.getText().toString()+"';";
                        database.QueryData(sqlUpdate);
                        updateListUpToTable(table);
                        builder.dismiss();
                    }
                });
            builder.show();
            }
        });

        danhSach_subData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(DanhMucAdmin.this);
                alertDialog.setTitle("Xóa trường dữ liệu");
                alertDialog.setMessage("Bạn có chắc là muốn xóa " + arrayList.get(i).getName() + " ?");
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int a) {
                        database.QueryData("DELETE FROM `"+table+"` WHERE `"+id+"` = '"+arrayList.get(i).getId()+"' ");
                        updateListUpToTable(table);
                    }
                });
                alertDialog.show();
                return false;
            }
        });

    }
    @SuppressLint("Range")
    private void updateListUpToTable(String name_table) {
        arrayList.clear();
        Cursor c = database.getData("SELECT * FROM `" + name_table + "`");
        while (c.moveToNext()) {
            arrayList.add(new SubDataItem(c.getInt(c.getColumnIndex(id)), c.getString(c.getColumnIndex(name))));
        }
        c.close();
        adminArrayAdapter.notifyDataSetChanged();
    }

    private void anhxa() {
        btn_tacGia = findViewById(R.id.tacGia);
        btn_danhMuc = findViewById(R.id.danhMuc);
        btn_nhaPH = findViewById(R.id.nha_PH);
        btn_nhaXB = findViewById(R.id.nha_XB);
        adding = findViewById(R.id.add_subdata);
        danhSach_subData = findViewById(R.id.danhsach_subdata);
        danhSach = findViewById(R.id.tenDanhSach);

    }
}
