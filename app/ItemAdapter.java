package com.application.test_baitaplon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.test_baitaplon.Model.BookModel;
import com.application.test_baitaplon.Model.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private Context context;
    private ArrayList<BookModel> arrItem;

    public ItemAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<BookModel> arr){
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
        BookModel item = arrItem.get(position);
        if(item == null) {return;}
//        holder.imgItem.setImageResource(item.getHinhAnh());
        holder.tvName.setText(item.getTensach());

    }

    @Override
    public int getItemCount() {
        if(arrItem != null) return arrItem.size();
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgItem;
        private TextView tvName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.img_item);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
