package adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.appsach.R;

import com.example.appsach.admin.adminQlUser;

import java.util.ArrayList;


import SQLite.sqlite;
import model.user;

public class adapterQlUser extends ArrayAdapter<user> {
    adminQlUser adminQlUser;
    private Context context;
    private ArrayList<user> listQlUser;
    private int id;
    private user u;
    private ImageView imgFix,imgDelete;

    private TextView txtNameCus,txtEmailCus,txtSdtCus,txtPassCus;
    private EditText edtNameCus,edtEmailCus,edtSdtCus,edtPassCus;

    public adapterQlUser(@NonNull Context context, int resource, @NonNull ArrayList<user> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listQlUser = objects;
        this.id = resource;
    }


    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        u = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.admin_item_user,parent,false);


         imgFix = convertView.findViewById(R.id.imgFix);
         imgDelete = convertView.findViewById(R.id.imgDelete);

         txtNameCus = convertView.findViewById(R.id.txtNameCus);
         txtEmailCus = convertView.findViewById(R.id.txtEmailCus);
         txtSdtCus = convertView.findViewById(R.id.txtSdtCus);
         txtPassCus = convertView.findViewById(R.id.txtPassCus);

        txtNameCus.setText(u.getName_user());
        txtEmailCus.setText(u.getEmail_user());
        txtSdtCus.setText(u.getSdt());
        txtPassCus.setText(u.getPassword());

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Dialog builder = new Dialog(getContext());

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thông báo");

                builder.setMessage("Bạn có chắc chắn muốn xóa tài khoản: "+u.getName_user()+" không?");
                builder.setPositiveButton("Xac nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqlite s = new sqlite(getContext(), R.string.databaseName+"", null, 1);
                        s.QueryData("DELETE FROM user WHERE  email ='"+u.getEmail_user()+"'OR name ='"+u.getName_user()+"'");
                        Toast.makeText(getContext(), "Xóa tài khoản thành công!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(),com.example.appsach.admin.adminQlUser.class);
                        context.startActivity(i);
                    }
                });
                builder.show();
            }
        });

        imgFix.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
//                Dialog alert = new Dialog(getContext());
                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
                View view = layoutInflater.inflate(R.layout.admin_dia_changeinfo, null);
                alert.setView(view);

                edtNameCus = view.findViewById(R.id.edtCusName);
                edtEmailCus = view.findViewById(R.id.edtCusMail);
                edtSdtCus = view.findViewById(R.id.edtCusPhone);
                edtPassCus = view.findViewById(R.id.edtCusPass);

                edtNameCus.setText(u.getName_user());
                edtEmailCus.setText(u.getEmail_user());
                edtSdtCus.setText(u.getSdt());
                edtPassCus.setText(u.getPassword());

                alert.setTitle("Thông báo");
                alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sqlite s = new sqlite(getContext(), R.string.databaseName+"", null, 1);
                        s.QueryData("UPDATE user SET name = '" + edtNameCus.getText() + "', email = '"+edtEmailCus.getText()+"',phone = '"+edtSdtCus.getText()+"',password ='"+edtPassCus.getText()+"' " +
                                "WHERE id ='" + u.getId_user() + "'");
                        Toast.makeText(getContext(), "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(),com.example.appsach.admin.adminQlUser.class);
                        context.startActivity(i);

                    }
                });
                alert.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
    });
        return convertView;
}
}
