package adapter.Son;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.example.appsach.R;
import com.bumptech.glide.Glide;

import java.util.List;

import model.Son.Photo;

public class PhotoAdapter extends PagerAdapter {
    private Context context;
    private List<Photo> listPhoto;

    public PhotoAdapter(Context context, List<Photo> listPhoto) {
        this.context = context;
        this.listPhoto = listPhoto;
    }




    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.photo_item, container, false);
        ImageView imageView = view.findViewById(R.id.img_photo);

        Photo photo = listPhoto.get(position);
        if(photo != null){
            Glide.with(context).load(photo.getResoucreID()).into(imageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(listPhoto != null) return listPhoto.size();
        return 0;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
