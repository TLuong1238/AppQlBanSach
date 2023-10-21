package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appsach.R;
import java.util.List;
import model.category;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.categoryViewHolder> {

    private Context context;
    List<category> listCategory;

    public categoryAdapter(Context context, List<category> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    public void setData(List<category> c)
    {
        this.listCategory = c;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public categoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new categoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryViewHolder holder, int position) {

        category c = listCategory.get(position);
        if(c == null)
        {
            return;
        }
        holder.categoryName.setText(c.getNameCate());
        //Set layout ngang cho recycle view
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.recyclerBook.setLayoutManager(layoutManager);
        bookAdapter b = new bookAdapter(c.getItem(), context.getApplicationContext());
        holder.recyclerBook.setAdapter(b);
    }

    @Override
    public int getItemCount() {
        if(listCategory != null)
        {
            return listCategory.size();
        }
        return 0;
    }


    public static class categoryViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView categoryName;
        RecyclerView recyclerBook;
        public categoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.txtCate);
            recyclerBook = itemView.findViewById(R.id.recycleBook);
        }
    }



}
