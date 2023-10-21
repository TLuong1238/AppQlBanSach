package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appsach.Category.LayoutInfoItem;
import com.example.appsach.R;
import java.util.List;
import model.Son.Item;

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.bookViewHolder> {

    private List<Item> listBook;
    private Context context;

    public bookAdapter(List<Item> listBook, Context context) {
        this.listBook = listBook;
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Item> b)
    {
        this.listBook = b;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public bookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book,parent,false);
        return new bookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookViewHolder holder, int position) {
            Item book = listBook.get(position);
            if(book == null)
            {
                return;
            }
            holder.imgBook.setImageBitmap(book.getImage());
            holder.title.setText(book.getName());

            holder.layout_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelectedRecycleItem(book);
                }
            });

    }

    private void onSelectedRecycleItem(Item item)
    {
        Intent i = new Intent(context, LayoutInfoItem.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putString("name",item.getName());
        i.putExtra("name_item",bundle);
        context.startActivity(i);

    }
    @Override
    public int getItemCount() {
        if(listBook != null)
        {
            return listBook.size();
        }
        return 0;
    }

    public class bookViewHolder extends RecyclerView.ViewHolder
    {
        private CardView layout_item;
        private ImageView imgBook;
        private TextView title;
        public bookViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item = itemView.findViewById(R.id.layout_item);
            imgBook = itemView.findViewById(R.id.imgBook);
            title = (itemView.findViewById(R.id.txtItem));
        }
    }
}
