package adapter.Son;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appsach.R;

import java.util.ArrayList;
import java.util.List;

import model.Son.SubDataItem;


public class AdminArrayAdapter extends ArrayAdapter<SubDataItem> {
    private Activity activity;

    private ArrayList<SubDataItem> arr;

    private int idLayout;


    public AdminArrayAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<SubDataItem> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.idLayout = resource;
        this.arr = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);
        TextView tv1 = convertView.findViewById(R.id.id_item);
        TextView tv2 = convertView.findViewById(R.id.name_item);
        tv1.setText(Integer.toString(arr.get(position).getId()));
        tv2.setText(arr.get(position).getName());
        return convertView;
    }
}
