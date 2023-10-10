package com.example.appsach.Profile;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

import model.user;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txtUserName,txtUserEmail;
    private ImageView imgCart,imgBox,imgBoxed;

    private CardView cardInfo,cardChange;
    private Button btnLogout;
    private user newUser;

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    public profileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment profileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static profileFragment newInstance(String param1, String param2) {

        profileFragment fragment = new profileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        //
        txtUserName.setText(newUser.getName_user());
        txtUserEmail.setText(newUser.getEmail_user());
        //
        //
        //
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