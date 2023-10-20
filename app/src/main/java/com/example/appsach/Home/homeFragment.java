package com.example.appsach.Home;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.appsach.Category.LayoutInfoItem;
import com.example.appsach.Profile.GioHang;
import com.example.appsach.R;
import com.example.appsach.StartProject.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.bookAdapter;
import adapter.categoryAdapter;
import model.Book;
import model.Son.Item;
import model.category;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {
    private RecyclerView recycleHome;

    private Toolbar toolhome;
    private MenuItem menuItemSearch,menuItemCart;
    private SearchView searchView;
    private ViewFlipper flipperHome;

    //
    private List<category> listCategories;
    //
    private ArrayList<Item> listItem;
    private ArrayList<Item> allItem;
    private ArrayList<Item> searchList;
    private categoryAdapter categoryAdapter;
    private bookAdapter bookAdapter;
    //

    public homeFragment() {
        // Required empty public constructor
    }

    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        //
        recycleHome = view.findViewById(R.id.recycleHome);
        toolhome = view.findViewById(R.id.toolHome);
        flipperHome = view.findViewById(R.id.flipperHome);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        assert appCompatActivity != null;
        appCompatActivity.setSupportActionBar(toolhome);
        setHasOptionsMenu(true);

        //
         Animation slide_in = AnimationUtils.loadAnimation(appCompatActivity.getApplicationContext(),R.anim.slide_out_right);
         Animation slide_out = AnimationUtils.loadAnimation(appCompatActivity.getApplicationContext(),R.anim.slide_in_right);
         flipperHome.setFlipInterval(2500);
         flipperHome.setAutoStart(true);
         flipperHome.setInAnimation(slide_in);
         flipperHome.setOutAnimation(slide_out);
        //
        getAllItem();
        getLisIttem();
        bookAdapter = new bookAdapter(listItem,getContext());

        for (Item b: listItem) {
            ImageView i = new ImageView(getContext());
            i.setImageBitmap(b.getImage());
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), LayoutInfoItem.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",b.getName());
                    intent.putExtra("name_item",bundle);
                    startActivity(intent);
                }
            });
            flipperHome.addView(i);
        }


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recycleHome.setLayoutManager(layoutManager);
        //

        categoryAdapter = new categoryAdapter(getContext(),getListCate());
        recycleHome.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        return view;
    }

    @SuppressLint("Range")
    private List<Item> getLisIttem(){
        listItem =new ArrayList<>();
        sqlite s = new sqlite(getContext(),R.string.databaseName+"",null,1);
        s.createTable();
        s.updateDB();
        Cursor c= s.getData("SELECT * FROM book LIMIT 10");
        while (c.moveToNext())
        {
            byte[] bytes = c.getBlob(c.getColumnIndex("hinhanh"));
            Item i = new Item(c.getString(c.getColumnIndex("tieude")), BitmapUtils.getImage(bytes));
            listItem.add(i);
        }
//
        return listItem;
    }
    @SuppressLint("Range")
    private List<Item> getAllItem(){
        allItem =new ArrayList<>();
        sqlite s = new sqlite(getContext(),R.string.databaseName+"",null,1);
        Cursor c= s.getData("SELECT * FROM book");
        while (c.moveToNext())
        {
            byte[] bytes = c.getBlob(c.getColumnIndex("hinhanh"));
            Item i = new Item(c.getString(c.getColumnIndex("tieude")), BitmapUtils.getImage(bytes));
            allItem.add(i);
        }
//
        return listItem;
    }
    @SuppressLint("Range")
    private List<category> getListCate() {
        listCategories= new ArrayList<>();

        sqlite s = new sqlite(getContext(),R.string.databaseName+"",null,1);
        Cursor c= s.getData("SELECT * FROM danh_muc LIMIT 5");

        while (c.moveToNext())
        {
            String name = c.getString(c.getColumnIndex("ten_danhmuc"));
            listCategories.add(new category(name,listItem));
        }
        return listCategories;
    }
    @SuppressLint("Range")
    private List<category> getListCate(ArrayList<Item> b) {
        listCategories= new ArrayList<>();
        listCategories.add(new category("Sách cần tìm:",b));
        sqlite s = new sqlite(getContext(),R.string.databaseName+"",null,1);
        Cursor c= s.getData("SELECT * FROM danh_muc LIMIT 5");

        while (c.moveToNext())
        {
             String name = c.getString(c.getColumnIndex("ten_danhmuc"));
            listCategories.add(new category(name,listItem));
        }
        return listCategories;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        menuItemSearch = menu.findItem(R.id.action_search);
        searchView =(SearchView) menuItemSearch.getActionView();
        assert searchView != null;
        searchView.setIconified(true);
        searchView.setQueryHint("Type to Search");
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();
                if(query.length()>0)
                {
                    for (int i = 0; i < allItem.size(); i++) {
                        if(allItem.get(i).getName().toUpperCase().contains(query.toUpperCase()))
                        {
                            searchList.add(allItem.get(i));
                        }
                    }
                    categoryAdapter = new categoryAdapter(getContext(),getListCate(searchList));
                    recycleHome.setAdapter(categoryAdapter);
                }else
                {
                    categoryAdapter = new categoryAdapter(getContext(),getListCate());
                    recycleHome.setAdapter(categoryAdapter);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();
                if(newText.length()>0)
                {
                    for (int i = 0; i < allItem.size(); i++) {
                        if(allItem.get(i).getName().toUpperCase().contains(newText.toUpperCase()))
                        {
                            searchList.add(allItem.get(i));
                        }
                    }
                    categoryAdapter = new categoryAdapter(getContext(),getListCate(searchList));
                    recycleHome.setAdapter(categoryAdapter);

                }else
                {
                    categoryAdapter = new categoryAdapter(getContext(),getListCate());
                    recycleHome.setAdapter(categoryAdapter);
                }
                return false;
            }
        });



        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.cart)
        {
            startActivity(new Intent(getContext(), GioHang.class));
        }

        return super.onOptionsItemSelected(item);
    }
}