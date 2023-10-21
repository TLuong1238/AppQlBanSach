package adapter.Son;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.appsach.R;
import java.util.List;
import model.Son.SubDataItem;

public class AdaterSpinner extends ArrayAdapter<SubDataItem> {
    public AdaterSpinner(@NonNull Context context, int resource, @NonNull List<SubDataItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_spinner,parent,false);
        TextView tvSpinner = convertView.findViewById(R.id.name_spinner);
        SubDataItem item = this.getItem(position);
        if (item != null){
            tvSpinner.setText(item.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_spinner,parent,false);
        TextView tvItemSpinner = convertView.findViewById(R.id.tv_ItemSpinner);
        SubDataItem item = this.getItem(position);
        if (item != null){
            tvItemSpinner.setText(item.getName());
        }
        return convertView;
    }
}
