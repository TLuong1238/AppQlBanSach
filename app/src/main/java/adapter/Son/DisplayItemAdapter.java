package adapter.Son;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appsach.R;

import java.util.ArrayList;

import model.Son.Item;

public class DisplayItemAdapter extends RecyclerView.Adapter<DisplayItemAdapter.ItemViewHolder>{
    private Context context;
    private ArrayList<Item> arrItem;

    public DisplayItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Item> arr){
        this.arrItem = arr;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_display_card_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = arrItem.get(position);
        if(item == null) {return;}
        holder.imgItem.setImageBitmap(item.getImage());
        holder.tvName.setText(item.getName());
        String gia = item.getGia() + " VND";
        holder.tvGia.setText(gia);

    }

    @Override
    public int getItemCount() {
        if(arrItem != null) return arrItem.size();
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgItem;
        private TextView tvName;

        private TextView tvGia;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.img_item1);
            tvName = itemView.findViewById(R.id.tv_name1);
            tvGia = itemView.findViewById(R.id.tv_priceItem);
        }
    }
}
