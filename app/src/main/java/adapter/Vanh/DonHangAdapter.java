package adapter.Vanh;//package com.example.profilecart.adapter;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.example.profilecart.R;
//import com.example.profilecart.activity.GioHang;
//import com.example.profilecart.activity.Quanlidon;
//import com.example.profilecart.model.Donhang;
//import com.example.profilecart.model.Sanpham;
//
//import java.util.ArrayList;
//
//public class DonHangAdapter extends BaseAdapter {
//
//    Context context;
//    ArrayList<Donhang> arrayDonhang;
//    Quanlidon quanlidon;
//
//    public DonHangAdapter(Context context, ArrayList<Donhang> arrayDonhang) {
//        this.context = context;
//        this.arrayDonhang = arrayDonhang;
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    public class ViewHolder{
//        public TextView txtMadonhang;
//        public Button btnXemdon;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder = null;
//
//        if(view == null){
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.item_quanlidon,null);
//            viewHolder.txtMadonhang = view.findViewById(R.id.txtMadonhang);
//            viewHolder.btnXemdon = view.findViewById(R.id.btnXemdon);
//            view.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        Donhang giohang = (Donhang) getItem(i);
//        viewHolder.txtMadonhang.setText(giohang.getMa_donhang());
//
//        return view;
//    }
//}
