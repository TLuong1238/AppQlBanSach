package adapter.Vanh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appsach.Profile.GioHang;
import com.example.appsach.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import SQLite.sqlite;
import model.Vanh.ItemGiohang;

public class GiohangAdapter extends BaseAdapter {

    Context context;
    ArrayList<ItemGiohang> arraygiohang;
    GioHang gioHang;

    public GiohangAdapter(Context context, ArrayList<ItemGiohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        public TextView txtTensp, txtGiasp;
        public ImageView imgSanpham;
        public Button btnCong, btnValue, btnTru;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_giohang,null);
            viewHolder.txtTensp = view.findViewById(R.id.txtTensp);
            viewHolder.txtGiasp = view.findViewById(R.id.txtGiasp);
            viewHolder.imgSanpham = view.findViewById(R.id.imgSanpham);
            viewHolder.btnCong = view.findViewById(R.id.btnCong);
            viewHolder.btnValue = view.findViewById(R.id.btnValue);
            viewHolder.btnTru = view.findViewById(R.id.btnTru);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        ItemGiohang giohang = (ItemGiohang) getItem(i);
        viewHolder.txtTensp.setText(giohang.getTen_sanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGiasp.setText(decimalFormat.format(giohang.getGiasp()) + " vnd ");
        viewHolder.imgSanpham.setImageBitmap(giohang.getHinhanh());
        viewHolder.btnValue.setText(giohang.getSoluong() + "");
        int soluong = Integer.parseInt(viewHolder.btnValue.getText().toString());

        if(soluong >=10){
            viewHolder.btnCong.setVisibility(View.INVISIBLE);
            viewHolder.btnTru.setVisibility(View.VISIBLE);
        }
        else if(soluong <=1){
            viewHolder.btnTru.setVisibility(View.INVISIBLE);
        }
        else if(soluong >=1){
            viewHolder.btnCong.setVisibility(View.VISIBLE);
            viewHolder.btnTru.setVisibility(View.VISIBLE);
        }

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluongmoinhat = Integer.parseInt(finalViewHolder.btnValue.getText().toString()) + 1;
                int soluonghientai = gioHang.lstGiohang.get(i).getSoluong();
                long giahientai = gioHang.lstGiohang.get(i).getGiasp();
                gioHang.lstGiohang.get(i).setSoluong(soluongmoinhat);

                long total = (giahientai * soluongmoinhat) / soluonghientai;
                gioHang.lstGiohang.get(i).setGiasp(total);

                int maSP = gioHang.lstGiohang.get(i).getId_sanpham();
                sqlite s = new sqlite(view.getContext(), R.string.databaseName+"",null,1);
                s.QueryData("UPDATE gio_hang SET soluong = '"+ soluongmoinhat +"' WHERE id_sach = '"+ maSP +"'");


                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiasp.setText(decimalFormat.format(total) + " vnd ");
                com.example.appsach.Profile.GioHang.Tongtien();
                if(soluongmoinhat>9){
                    finalViewHolder.btnCong.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(String.valueOf(soluongmoinhat));
                }
                else{
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(String.valueOf(soluongmoinhat));
                }
            }
        });

        viewHolder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluongmoinhat = Integer.parseInt(finalViewHolder.btnValue.getText().toString()) - 1;
                int soluonghientai = gioHang.lstGiohang.get(i).getSoluong();
                long giahientai = gioHang.lstGiohang.get(i).getGiasp();
                gioHang.lstGiohang.get(i).setSoluong(soluongmoinhat);

                long total = (giahientai * soluongmoinhat) / soluonghientai;
                gioHang.lstGiohang.get(i).setGiasp(total);

                int maSP = gioHang.lstGiohang.get(i).getId_sanpham();
                sqlite s = new sqlite(view.getContext(), R.string.databaseName+"",null,1);
                s.QueryData("UPDATE gio_hang SET soluong = '"+ soluongmoinhat +"' WHERE id_sach = '"+ maSP +"'");

                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtGiasp.setText(decimalFormat.format(total) + " vnd ");
                com.example.appsach.Profile.GioHang.Tongtien();
                if(soluongmoinhat<2){
                    finalViewHolder.btnTru.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(String.valueOf(soluongmoinhat));
                }
                else{
                    finalViewHolder.btnCong.setVisibility(View.VISIBLE);
                    finalViewHolder.btnTru.setVisibility(View.VISIBLE);
                    finalViewHolder.btnValue.setText(String.valueOf(soluongmoinhat));
                }
            }
        });

        return view;
    }
}
