package adapter.Son;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appsach.Category.LayoutInfoItem;

import com.example.appsach.R;

import java.util.ArrayList;

import model.Book;
import model.Son.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private Context context;
    private ArrayList<Item> arrItem;

    public ItemAdapter(ArrayList<Item> arrItem,Context context) {
        this.context = context;
        this.arrItem = arrItem;
    }

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Item> arr){
        this.arrItem = arr;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_a_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = arrItem.get(position);
        if(item == null) {return;}
        holder.imgItem.setImageBitmap(item.getImage());
        holder.tvName.setText(item.getName());
        //Luong





    }


    @Override
    public int getItemCount() {
        if(arrItem != null) return arrItem.size();
        return 0;
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imgItem;
        private final TextView tvName;



        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.img_item);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
