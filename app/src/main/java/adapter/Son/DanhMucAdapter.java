package adapter.Son;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.appsach.R;

import java.util.ArrayList;

import model.Son.DanhMuc;

public class DanhMucAdapter extends ArrayAdapter<DanhMuc> {
    private Activity activity;

    private ArrayList<DanhMuc> arr;

    private int idLayout;

    public DanhMucAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<DanhMuc> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.idLayout =resource;
        this.arr = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        //ImageView imgView = convertView.findViewById(R.id.img_danhmuc);
        TextView textView = convertView.findViewById(R.id.tv_danhmuc);
        //imgView.setImageResource(arr.get(position).getResoucreImg());
        textView.setText(arr.get(position).getName());
        return convertView;
    }
}
