package adapter.Son;

import android.app.AlertDialog;
import SQLite.sqlite;
import android.content.Context;
import android.content.DialogInterface;
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
import model.Son.SubDataItem;


public class AdminArrayAdapter extends ArrayAdapter<SubDataItem> {
    private Context activity;

    private ArrayList<SubDataItem> arr;

    private int idLayout;

    private String table, id, name;


    public AdminArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<SubDataItem> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.idLayout = resource;
        this.arr = objects;
    }

    public void getInforTable(String table, String id, String name) {
        this.table = table;
        this.id = id;
        this.name = name;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        convertView = inflater.inflate(idLayout, null);
        TextView tv1 = convertView.findViewById(R.id.id_item);
        TextView tv2 = convertView.findViewById(R.id.name_item);
        ImageView img = convertView.findViewById(R.id.btn_delete);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlite database = new sqlite(view.getContext(), R.string.databaseName + "", null, 1);
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                alertDialog.setTitle("Xóa trường dữ liệu");
                alertDialog.setMessage("Bạn có chắc là muốn xóa " + arr.get(position).getName() + " ?");
                alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int a) {
                        database.QueryData("DELETE FROM `" + table + "` WHERE `" + id + "` = '" + arr.get(position).getId() + "' ");
                        arr.remove(arr.get(position));
                        notifyDataSetChanged();
                    }
                });
                database.close();
                alertDialog.show();
            }
        });
        tv1.setText(Integer.toString(arr.get(position).getId()));
        tv2.setText(arr.get(position).getName());
        return convertView;
    }
}
