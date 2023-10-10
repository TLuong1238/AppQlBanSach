package com.example.appsach.Home;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import com.example.appsach.R;

import java.util.ArrayList;
import java.util.List;

import adapter.bookAdapter;
import adapter.categoryAdapter;
import model.Book;
import model.category;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link homeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class homeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycleHome;



    Toolbar toolhome;
    private MenuItem menuItem;
    private SearchView searchView;
    ViewFlipper flipperHome;



    //
    List<category> listCategories;
    List<Book> listBooks = new ArrayList<>();
    List<Book> listBooks2 = new ArrayList<>();
    List<Book> listBooks3 = new ArrayList<>();
    List<Book> listBooks4 = new ArrayList<>();
    List<Book> listBooks5 = new ArrayList<>();
    ArrayList<Book> searchList;
    private categoryAdapter categoryAdapter;
    private bookAdapter bookAdapter;

    //
    ImageView img1,img2,img3,img4;
    public homeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment homeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static homeFragment newInstance(String param1, String param2) {
        homeFragment fragment = new homeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recycleHome = view.findViewById(R.id.recycleHome);
        toolhome = view.findViewById(R.id.toolHome);
        flipperHome = view.findViewById(R.id.flipperHome);




        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        assert appCompatActivity != null;
        appCompatActivity.setSupportActionBar(toolhome);
        //
         Animation slide_in = AnimationUtils.loadAnimation(appCompatActivity.getApplicationContext(),R.anim.slide_out_right);
         Animation slide_out = AnimationUtils.loadAnimation(appCompatActivity.getApplicationContext(),R.anim.slide_in_right);
         flipperHome.setFlipInterval(2500);
         flipperHome.setAutoStart(true);
         flipperHome.setInAnimation(slide_in);
         flipperHome.setOutAnimation(slide_out);


        //
        getListBook();
        bookAdapter = new bookAdapter(listBooks,getContext());
        for (Book b: listBooks) {
            ImageView i = new ImageView(getContext());
            i.setImageResource(b.getSourceId());
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), BookDetail.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("objectBook",b);
                    i.putExtras(bundle);
                    startActivity(i);
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

    private List<Book> getListBook(){
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks2.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));

        listBooks3.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks3.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks3.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks3.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks3.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks3.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));
        listBooks3.add(new Book(R.drawable.song_of_ice_and_fire,"Chuyến đi của thanh xuân"));

        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));
        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));
        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));
        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));
        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));
        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));
        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));
        listBooks4.add(new Book(R.drawable.song_of_ice_and_fire,"All things you know about me are wrong"));

        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks5.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));


        listBooks.add(new Book(R.drawable.khong_gia_dinh,"Những loài hoa có gai"));
        listBooks.add(new Book(R.drawable.song_of_ice_and_fire,"Song of ice and fire"));
        listBooks.add(new Book(R.drawable.godfather,"The god father"));
        listBooks.add(new Book(R.drawable.khong_gia_dinh,"Khong gia dinh"));
        listBooks.add(new Book(R.drawable.nha_gia_kim,"Nha gia kim"));

        listBooks.add(new Book(R.drawable.song_of_ice_and_fire,"Song of ice and fire"));
        listBooks.add(new Book(R.drawable.godfather,"The god father"));
        listBooks.add(new Book(R.drawable.khong_gia_dinh,"Khong gia dinh"));
        listBooks.add(new Book(R.drawable.nha_gia_kim,"Nha gia kim"));
        return listBooks;
    }
    private List<category> getListCate() {
        listCategories= new ArrayList<>();

        listCategories.add(new category("Sách được ưa thích",listBooks));
        listCategories.add(new category("Được tìm đọc nhiều nhất",listBooks2));
        listCategories.add(new category("Được mua nhiều nhất",listBooks3));
        listCategories.add(new category("Sách mới",listBooks4));
        listCategories.add(new category("Sách đề cử",listBooks5));

        return listCategories;
    }
    private List<category> getListCate(List<Book> b) {
        listCategories= new ArrayList<>();
        listCategories.add(new category("Sách cần tìm:",b));
        listCategories.add(new category("Sách được ưa thích",listBooks));
        listCategories.add(new category("Được tìm đọc nhiều nhất",listBooks2));
        listCategories.add(new category("Được mua nhiều nhất",listBooks3));
        listCategories.add(new category("Sách mới",listBooks4));
        listCategories.add(new category("Sách đề cử",listBooks5));

        return listCategories;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        menuItem = menu.findItem(R.id.action_search);
        searchView =(SearchView) menuItem.getActionView();
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
                    for (int i = 0; i < listBooks.size(); i++) {
                        if(listBooks.get(i).getTitle().toUpperCase().contains(query.toUpperCase()))
                        {
                            searchList.add(listBooks.get(i));
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
                    for (int i = 0; i < listBooks.size(); i++) {
                        if(listBooks.get(i).getTitle().toUpperCase().contains(newText.toUpperCase()))
                        {
                            searchList.add(listBooks.get(i));
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
}