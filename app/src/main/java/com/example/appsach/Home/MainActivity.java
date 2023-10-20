package com.example.appsach.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ViewFlipper;

import com.example.appsach.R;
import com.example.appsach.Category.cateFragment;
import com.example.appsach.databinding.ActivityMainBinding;
import com.example.appsach.Profile.profileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import model.user;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navHome;
    ActivityMainBinding binding;
    private user newUser;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

//    Boolean check = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapping();
        sp = getSharedPreferences("LoginData",MODE_PRIVATE);
        editor = sp.edit();
        setContentView(binding.getRoot());
        replaceFragment(new homeFragment());
        ActionNav();
        newUser = new user(sp.getInt("id",0),sp.getString("name",""),sp.getString("email",""),
                sp.getString("pass",""),sp.getString("phone",""));

//        Intent i = getIntent();
//        Bundle b = i.getBundleExtra("key");
//        check = b.getBoolean("check");
//        if(check == false){
//            replaceFragment(new cateFragment());
//        }
    }
    private void mapping() {
        navHome = findViewById(R.id.navHome);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        Bundle bundle = getIntent().getExtras();
//        newUser = (model.user) bundle.get("object_user");
    }
    private void ActionNav() {
        binding.navHome.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.itemHome){
                replaceFragment(new homeFragment());
            }
            if(item.getItemId() == R.id.itemCate){
                replaceFragment(new cateFragment());
            }
            if(item.getItemId() == R.id.itemProfile){
                replaceFragment(new profileFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.constraint_layout,fragment);
        fragmentTransaction.commit();
    }

    public user getNewUser() {
        return newUser;
    }

    public void setNewUser(user newUser) {
        this.newUser = newUser;
    }
}