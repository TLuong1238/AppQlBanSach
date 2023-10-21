package adapter.Vanh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appsach.Profile.Chitiethoadon;
import com.example.appsach.R;


import java.util.ArrayList;

import SQLite.sqlite;
import model.Vanh.Donhang;

public class AdapterAdminDonhang extends ArrayAdapter {

    private Activity adminQuanlidon;
    private ArrayList<Donhang> arrDonhang;
    private int idLayout;
    Context context = AdapterAdminDonhang.this.getContext();

    public AdapterAdminDonhang(@NonNull Context context, int resource, @NonNull ArrayList<Donhang> objects) {
        super(context, resource, objects);
        this.adminQuanlidon = (Activity) context;
        this.idLayout = resource;
        this.arrDonhang = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = adminQuanlidon.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        TextView txtMadonhang = convertView.findViewById(R.id.txtMadonhang);
        Button btnXemdon = convertView.findViewById(R.id.btnXemdon);
        Button btnXuly = convertView.findViewById(R.id.btnXuly);

        txtMadonhang.setText(arrDonhang.get(position).getMa_donhang()+"");
        String ma = (String) txtMadonhang.getText();

        btnXemdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chitiethoadon.class);
                intent.putExtra("ma_donhang", ma);
                context.startActivity(intent);
            }
        });

        btnXuly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlite s = new sqlite(getContext(),R.string.databaseName+"",null,1);
                s.QueryData("UPDATE tbl_hoadon SET tinhtrang = 0 WHERE ma_donhang = '"+ ma +"' ");
                arrDonhang.remove(position);
                notifyDataSetChanged();
                Toast.makeText(getContext(),"Xác nhận đơn hàng thành công", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }

}
