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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appsach.Profile.Chitiethoadon;
import com.example.appsach.R;


import java.util.ArrayList;

import model.Vanh.Donhang;

public class AdapterAdminDoanhthu extends ArrayAdapter {

    private Activity adminDoanhthu;
    private ArrayList<Donhang> lstDonHang;
    private int idLayout;
    Context context = AdapterAdminDoanhthu.this.getContext();

    public AdapterAdminDoanhthu(@NonNull Context context, int resource, @NonNull ArrayList<Donhang> objects) {
        super(context, resource, objects);
        this.adminDoanhthu = (Activity) context;
        this.idLayout = resource;
        this.lstDonHang = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = adminDoanhthu.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        TextView txtMadonhang = convertView.findViewById(R.id.txtMadonhang);
        Button btnXemdon = convertView.findViewById(R.id.btnXemdon);

        txtMadonhang.setText(lstDonHang.get(position).getMa_donhang()+"");
        String ma = (String) txtMadonhang.getText();

        btnXemdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Chitiethoadon.class);
                intent.putExtra("ma_donhang", ma);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

}
