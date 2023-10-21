package com.example.appsach.Profile;

import static android.content.Context.MODE_PRIVATE;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appsach.Home.MainActivity;
import com.example.appsach.R;
import com.example.appsach.StartProject.LoginActivity;
import SQLite.sqlite;
import model.user;

public class profileFragment extends Fragment {

    private TextView txtUserName,txtUserEmail;
    private ImageView imgCart,imgBoxed;

    private CardView cardInfo,cardChange;
    private Button btnLogout;
    private user newUser;

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    public profileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //
        MainActivity mainActivity = (MainActivity) getActivity();
        newUser = mainActivity.getNewUser();
        //
        sp = getActivity().getSharedPreferences("LoginData",MODE_PRIVATE);
        editor = sp.edit();
        //

        txtUserName = view.findViewById(R.id.txtUserName);
        txtUserEmail = view.findViewById(R.id.txtUserEmail);
        cardInfo = view.findViewById(R.id.cardInfo);
        cardChange = view.findViewById(R.id.cardChange);
        btnLogout = view.findViewById(R.id.btnLogout);
        imgCart = view.findViewById(R.id.imgCart);
        imgBoxed = view.findViewById(R.id.imgBoxed);

        //
        txtUserName.setText(newUser.getName_user());
        txtUserEmail.setText(newUser.getEmail_user());
        //
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), GioHang.class);
                startActivity(i);
            }
        });

        imgBoxed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlite db = new sqlite(getContext(),R.string.databaseName+"",null,1);
                db.QueryData(("CREATE TABLE IF NOT EXISTS tbl_hoadon(id_hoadon INTEGER PRIMARY KEY AUTOINCREMENT, id_taikhoan INTEGER, ma_donhang INTEGER, tinhtrang INTEGER)"));
                Intent i = new Intent(getContext(), Quanlidon.class);
                startActivity(i);
            }
        });

        cardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ThongTin.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",newUser);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        //
        cardChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                View view = getLayoutInflater().inflate(R.layout.dialog_xac_nhan,null);
                EditText edtXachNhan= view.findViewById(R.id.edtXacNhan);
                alert.setView(view);
                alert.setTitle("Thông báo");
                alert.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String str = edtXachNhan.getText().toString();
                        if(str.equals(newUser.getPassword()))
                        {
                            Toast.makeText(getContext(), "Mật khẩu chính xác!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getContext(),ChangePass.class);
                            Bundle bundle1 = new Bundle();
                            bundle1.putSerializable("objectUser",newUser);
                            i.putExtras(bundle1);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alert.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

            }

        });
        //
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("Thông báo");
                dialog.setMessage("Bạn có chắc chắn muốn đăng xuất không?");
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editor.clear();
                        editor.apply();
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                });
                dialog.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }

        });
        return view;
    }


}