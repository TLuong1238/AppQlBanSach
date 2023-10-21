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

public class AdapterDonhang extends ArrayAdapter {

    private Activity Quanlidon;
    private ArrayList<Donhang> lstDonHang;
    private int idLayout;
    Context context = AdapterDonhang.this.getContext();

    public AdapterDonhang(@NonNull Context context, int resource, @NonNull ArrayList<Donhang> objects) {
        super(context, resource, objects);
        this.Quanlidon = (Activity) context;
        this.idLayout = resource;
        this.lstDonHang = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = Quanlidon.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        TextView txtMadonhang = convertView.findViewById(R.id.txtMadonhang);
        TextView txtState = convertView.findViewById(R.id.txtState);
        Button btnXemdon = convertView.findViewById(R.id.btnXemdon);

        txtMadonhang.setText(lstDonHang.get(position).getMa_donhang()+"");
        String ma = (String) txtMadonhang.getText();

        int trangthai = lstDonHang.get(position).getTrangthai();

        if(trangthai == 0){
            txtState.setText("Đã xử lí");
        }
        else{
            txtState.setText("Chờ xử lí");
        }

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
