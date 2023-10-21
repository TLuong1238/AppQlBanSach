package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.appsach.R;
import java.util.ArrayList;
import model.Son.Item;


public class adapterThongKe extends ArrayAdapter<Item> {
    private Context context;
    private ArrayList<Item> arrTk;
    private int id;

    private TextView txtName,txtGia,txtLuotMua,txtThuNhap;
    private ImageView imgTk;

    private Item item;

    public adapterThongKe(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.arrTk = objects;
        this.id = resource;
    }
    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        item = getItem(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.admin_item_thongke,parent,false);


        txtName = convertView.findViewById(R.id.txtNameTk);
        txtGia = convertView.findViewById(R.id.txtGiaTk);
        txtLuotMua = convertView.findViewById(R.id.txtLuotMuaTk);
        txtThuNhap = convertView.findViewById(R.id.txtDoanhthu);
        imgTk = convertView.findViewById(R.id.imgTk);

        txtName.setText(item.getName());
        txtGia.setText(item.getGia());
        String str = String.valueOf(item.getLuotmua());
        txtLuotMua.setText(str);
        imgTk.setImageBitmap(item.getImage());

        return convertView;
    }
}
