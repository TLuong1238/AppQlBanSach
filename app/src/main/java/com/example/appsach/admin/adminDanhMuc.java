package com.example.appsach.admin;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.appsach.R;
import java.util.ArrayList;
import SQLite.sqlite;
import adapter.Son.AdminArrayAdapter;
import model.Son.SubDataItem;

public class adminDanhMuc extends Activity {
    private Button btn_tacGia, btn_nhaXB, btn_nhaPH, btn_danhMuc, adding;

    private TextView danhSach;

    private ImageView imgBack;

    private ListView danhSach_subData;

    private AdminArrayAdapter adminArrayAdapter;

    private ArrayList<SubDataItem> arrayList;

    private sqlite database;
    private String table, id, name;

    private int check;

    @Override
    @SuppressLint("Range")
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuc_admin);
        database = new sqlite(this, R.string.databaseName + "", null, 1);

        anhxa();
        //default la tacgia
        danhSach.setText("Danh sách tác giả");
        table = "tac_gia";
        id = "id_tacgia";
        name = "ten_tacgia";
        arrayList = new ArrayList<>();
        Cursor cursor = database.getData("SELECT * FROM tac_gia");
        while (cursor.moveToNext()) {
            arrayList.add(new SubDataItem(cursor.getInt(cursor.getColumnIndex("id_tacgia")), cursor.getString(cursor.getColumnIndex("ten_tacgia"))));
        }
        adminArrayAdapter = new AdminArrayAdapter(this, R.layout.admin_array_custom, arrayList);
        adminArrayAdapter.getInforTable(table, id, name);
        danhSach_subData.setAdapter(adminArrayAdapter);
        adminArrayAdapter.notifyDataSetChanged();

        //backAdmin
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminDanhMuc.this, MainAdmin.class));
                finish();
            }
        });

        //button
        btn_tacGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                danhSach.setText("Danh sách tác giả");
                table = "tac_gia";
                id = "id_tacgia";
                name = "ten_tacgia";
                adminArrayAdapter.getInforTable(table, id, name);
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
                adminArrayAdapter.getInforTable(table, id, name);
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
                adminArrayAdapter.getInforTable(table, id, name);
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
                adminArrayAdapter.getInforTable(table, id, name);
                updateListUpToTable(table);
            }
        });

        //Thêm 1 trường mới trong danh sách sub data
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog builder = new AlertDialog.Builder(adminDanhMuc.this).create();

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_execute_button, null);
                builder.setView(dialogView);
                TextView title_dialog = dialogView.findViewById(R.id.title_dialog);
                EditText ed_id = dialogView.findViewById(R.id.ed_idSubData);
                EditText ed_name = dialogView.findViewById(R.id.ed_nameSubData);
                Button btn_cancel = dialogView.findViewById(R.id.btn_cancelSub);
                Button btn_exeSub = dialogView.findViewById(R.id.btn_exeSubData);

                title_dialog.setText("Thêm dữ liệu");
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
                            Toast.makeText(adminDanhMuc.this, "Bạn đã thêm thành công", Toast.LENGTH_LONG).show();
                            updateListUpToTable(table);
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
                final AlertDialog builder = new AlertDialog.Builder(adminDanhMuc.this).create();
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_execute_button, null);
                builder.setView(dialogView);
                TextView title_dialog = dialogView.findViewById(R.id.title_dialog);
                EditText ed_id = dialogView.findViewById(R.id.ed_idSubData);
                EditText ed_name = dialogView.findViewById(R.id.ed_nameSubData);
                Button btn_cancel = dialogView.findViewById(R.id.btn_cancelSub);
                Button btn_exeSub = dialogView.findViewById(R.id.btn_exeSubData);

                title_dialog.setText("Sửa thông tin");
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
                        String sqlUpdate = "UPDATE `" + table + "` SET `" + name + "`='" + ed_name.getText().toString() + "'" +
                                " WHERE `" + id + "`='" + ed_id.getText().toString() + "';";
                        database.QueryData(sqlUpdate);
                        updateListUpToTable(table);
                        builder.dismiss();
                    }
                });
                builder.show();
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

        imgBack = findViewById(R.id.backDMAdmin);
        btn_tacGia = findViewById(R.id.tacGia);
        btn_danhMuc = findViewById(R.id.danhMuc);
        btn_nhaPH = findViewById(R.id.nha_PH);
        btn_nhaXB = findViewById(R.id.nha_XB);
        adding = findViewById(R.id.add_subdata);
        danhSach_subData = findViewById(R.id.danhsach_subdata);
        danhSach = findViewById(R.id.tenDanhSach);

    }
}
