package com.example.appsach.Category;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appsach.R;

import java.util.ArrayList;
import java.util.Random;

import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.Son.DanhMucAdapter;
import adapter.Son.ItemAdapter;
import adapter.Son.RecyclerItemClickListener;
import model.Son.DanhMuc;
import model.Son.Item;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class cateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private RecyclerView recyclerView_item;
    private ListView listView_DanhMuc;
    private ItemAdapter adapter_item;
    private ArrayList<DanhMuc> arrDanhMuc;
    EditText ed_Search;
    Cursor cursor;
    ArrayList<Item> arr;
    ImageView img_searching;
    private DanhMucAdapter danhMucAdapter;

    private sqlite database;

    public cateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static cateFragment newInstance(String param1, String param2) {
        cateFragment fragment = new cateFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout_danh_muc, container, false);
        database = new sqlite(getContext(), R.string.databaseName + "", null, 1);
        //Tạo bảng

        //
        arrDanhMuc = new ArrayList<>();
        ed_Search = view.findViewById(R.id.ed_timKiem1);
        listView_DanhMuc = view.findViewById(R.id.lv_danh_muc);
        recyclerView_item = view.findViewById(R.id.recyclerV_Item);
        img_searching = view.findViewById(R.id.img_searching1);

        //danh muc
        danhMucAdapter = new DanhMucAdapter(getContext(), R.layout.layout_custom_danhmuc, arrDanhMuc);
        getDataToListViewDanhMuc();
        listView_DanhMuc.setAdapter(danhMucAdapter);

        //recycleView
        adapter_item = new ItemAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);


//        insertSubData();


        getListData();
        recyclerView_item.requestFocus();
        recyclerView_item.setLayoutManager(gridLayoutManager);
        adapter_item.setData(arr);
        recyclerView_item.setAdapter(adapter_item);

        //ed_search
        searching();

        //setOnItemClickListener for RecycleView
        recyclerView_item.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView_item, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView ten = view.findViewById(R.id.tv_name);
                String name = ten.getText().toString();
                Intent intent = new Intent(getContext(), LayoutInfoItem.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putInt("idlayout", R.layout.layout_danh_muc);
                intent.putExtra("name_item", bundle);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        ////setOnItemClickListener for ListView
        listView_DanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView danhMuc = view.findViewById(R.id.tv_danhmuc);
                String search = danhMuc.getText().toString();
                arr.clear();
                updateRecycleViewUpToDanhMuc(search);
                adapter_item.setData(arr);
            }
        });

        ed_Search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != KeyEvent.ACTION_DOWN) return false;
                if (i == KeyEvent.KEYCODE_ENTER) {
                    String key_word = ed_Search.getText().toString();
                    finalSeaching(key_word);
                    return true;
                }
                return false;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void searching() {
        img_searching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ed_Search.getText().toString();
                finalSeaching(s);
            }
        });
    }

    private void finalSeaching(String s) {
        Intent intent = new Intent(getContext(), LayoutTimKiem.class);
        Bundle bundle = new Bundle();
        bundle.putString("key_word", s);
        intent.putExtra("key", bundle);
        ed_Search.setText("");
        startActivity(intent);
    }


    @SuppressLint("Range")
    private void getDataToListViewDanhMuc() {
        cursor = database.getData("select * from danh_muc");
        while (cursor.moveToNext()) {
            arrDanhMuc.add(new DanhMuc(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_danhmuc"))), cursor.getString(cursor.getColumnIndex("ten_danhmuc"))));
        }
        danhMucAdapter.notifyDataSetChanged();
    }

    @SuppressLint("Range")
    private void updateRecycleViewUpToDanhMuc(String s) {
        arr = new ArrayList<Item>();
        cursor = database.getData("select * from book " + "join danh_muc on danh_muc.id_danhmuc = book.id_danhmuc " + "where danh_muc.ten_danhmuc = '" + s + "';");
        while (cursor.moveToNext()) {
            byte[] temp = cursor.getBlob(6);
            arr.add(new Item(cursor.getString(1), BitmapUtils.getImage(temp)));
        }
    }

    private void getListData() {
        arr = new ArrayList<Item>();
        cursor = database.getData("select * from book");
        while (cursor.moveToNext()) {
            byte[] temp = cursor.getBlob(6);
            arr.add(new Item(cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(9)), BitmapUtils.getImage(temp)));
        }
    }

    @SuppressLint("Range")
    private void insertSubData() {
        ContentValues values = new ContentValues();
        SQLiteDatabase db = database.getWritableDatabase();
        values.put("id_nhaph", 1);
        values.put("ten_nhaph", "First News");
        db.insert("nha_phat_hanh", null, values);

        values.put("id_nhaph", 2);
        values.put("ten_nhaph", "Nhã Nam");
        db.insert("nha_phat_hanh", null, values);

        values.put("id_nhaph", 3);
        values.put("ten_nhaph", "Kim Đồng");
        db.insert("nha_phat_hanh", null, values);

        values.put("id_nhaph", 4);
        values.put("ten_nhaph", "Alpha Books");
        db.insert("nha_phat_hanh", null, values);

        values.put("id_nhaph", 5);
        values.put("ten_nhaph", "Trẻ");
        db.insert("nha_phat_hanh", null, values);

        values.put("id_nhaph", 6);
        values.put("ten_nhaph", "Văn Lang");
        db.insert("nha_phat_hanh", null, values);

        values.put("id_nhaph", 7);
        values.put("ten_nhaph", "Nhà XB Tổng hợp TP HCM");
        db.insert("nha_phat_hanh", null, values);


        values = new ContentValues();
        values.put("id_nhaxb", 1);
        values.put("ten_nhaxb", "Trẻ");
        db.insert("nha_xuatban", null, values);

        values.put("id_nhaxb", 2);
        values.put("ten_nhaxb", "Kim Đồng");
        db.insert("nha_xuatban", null, values);

        values.put("id_nhaxb", 3);
        values.put("ten_nhaxb", "Tổng hợp thành phố Hồ Chí Minh.");
        db.insert("nha_xuatban", null, values);

        values.put("id_nhaxb", 4);
        values.put("ten_nhaxb", "Hội Nhà văn Việt Nam");
        db.insert("nha_xuatban", null, values);

        values.put("id_nhaxb", 5);
        values.put("ten_nhaxb", "chính trị quốc gia sự thật");
        db.insert("nha_xuatban", null, values);

        values.put("id_nhaxb", 6);
        values.put("ten_nhaxb", "Phụ nữ");
        db.insert("nha_xuatban", null, values);

        values.put("id_nhaxb", 7);
        values.put("ten_nhaxb", "Lao Động");
        db.insert("nha_xuatban", null, values);

        values.put("id_nhaxb", 8);
        values.put("ten_nhaxb", "tư nhân Nhã Nam");
        db.insert("nha_xuatban", null, values);


        values = new ContentValues();
        values.put("id_tacgia", 1);
        values.put("ten_tacgia", "Nassim Nicholas Tales");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 2);
        values.put("ten_tacgia", "Nguyễn Nhật Ánh");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 3);
        values.put("ten_tacgia", "José Mauro de Vasconcelos");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 4);
        values.put("ten_tacgia", "Haruki Murakami");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 5);
        values.put("ten_tacgia", "Daniel Kahneman");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 6);
        values.put("ten_tacgia", "Napoleon Hill");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 7);
        values.put("ten_tacgia", "Adam Grant");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 8);
        values.put("ten_tacgia", "Nguyễn Huy Cận");
        db.insert("tac_gia", null, values);

        values.put("id_tacgia", 9);
        values.put("ten_tacgia", "Viktor Frankl");
        db.insert("tac_gia", null, values);


        values = new ContentValues();
        values.put("id_danhmuc", 1);
        values.put("ten_danhmuc", "Văn học");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 2);
        values.put("ten_danhmuc", "Kinh tế");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 3);
        values.put("ten_danhmuc", "Thiếu nhi");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 4);
        values.put("ten_danhmuc", "Kĩ năng sống");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 5);
        values.put("ten_danhmuc", "Khoa học - Kỹ thuật");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 6);
        values.put("ten_danhmuc", "Lịch sử");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 7);
        values.put("ten_danhmuc", "Âm - Nhạc - Họa");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 8);
        values.put("ten_danhmuc", "Triết học");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 9);
        values.put("ten_danhmuc", "Y học");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 10);
        values.put("ten_danhmuc", "Công nghệ thông tin");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 11);
        values.put("ten_danhmuc", "Tâm lý");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 12);
        values.put("ten_danhmuc", "Thể dục - Thể thao");
        db.insert("danh_muc", null, values);

        values.put("id_danhmuc", 13);
        values.put("ten_danhmuc", "Truyện tranh - Manga - Comic");
        db.insert("danh_muc", null, values);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ccnct);
        byte[] bytes = BitmapUtils.getByte(bitmap);

        values = new ContentValues();
        values.put("tieude", "Cây Cam Ngọt Của Tôi");
        values.put("gia ", 69000);
        values.put("id_tacgia ", 3);
        values.put("id_danhmuc ", 1);
        values.put("tomtat", "Hãy làm quen với Zezé, cậu bé tinh nghịch siêu hạng đồng thời cũng đáng yêu bậc nhất, với ước mơ lớn lên trở thành nhà thơ cổ thắt nơ bướm. Chẳng phải ai cũng công nhận khoản “đáng yêu” kia đâu nhé. Bởi vì, ở cái xóm ngoại ô nghèo ấy, nỗi khắc khổ bủa vây đã che mờ mắt người ta trước trái tim thiện lương cùng trí tưởng tượng tuyệt vời của cậu bé con năm tuổi.\n" + "\n" + "Có hề gì đâu bao nhiêu là hắt hủi, đánh mắng, vì Zezé đã có một người bạn đặc biệt để trút nỗi lòng: cây cam ngọt nơi vườn sau. Và cả một người bạn nữa, bằng xương bằng thịt, một ngày kia xuất hiện, cho cậu bé nhạy cảm khôn sớm biết thế nào là trìu mến, thế nào là nỗi đau, và mãi mãi thay đổi cuộc đời cậu.\n" + "Mở đầu bằng những thanh âm trong sáng và kết thúc lắng lại trong những nốt trầm hoài niệm, Cây cam ngọt của tôi khiến ta nhận ra vẻ đẹp thực sự của cuộc sống đến từ những điều giản dị như bông hoa trắng của cái cây sau nhà, và rằng cuộc đời thật khốn khổ nếu thiếu đi lòng yêu thương và niềm trắc ẩn. Cuốn sách kinh điển này bởi thế không ngừng khiến trái tim người đọc khắp thế giới thổn thức, kể từ khi ra mắt lần đầu năm 1968 tại Brazil.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 244);
        values.put("id_nhaxb", 4);
        values.put("id_nhaph", 2);
        db.insert("book", null, values);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tnd);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Thiên nga đen");
        values.put("gia ", 210000);
        values.put("id_tacgia ", 1);
        values.put("id_danhmuc ", 2);
        values.put("tomtat", "Thiên Nga Đen\n" + "\n" + "Trước khi khám phá ra thiên nga đen tồn tại trên đời (ở Úc), người ta vẫn tin rằng tất cả chim thiên nga trên đời đều có màu trắng. Phát hiện bất ngờ này đã thay đổi toàn bộ thế giới quan của nhân loại (về thiên nga).\n" + "\n" + "Chúng ta không biết rất nhiều thứ nhưng lại hành động như thể mình có khả năng dự đoán được mọi điều. Và trong cuốn sách này, tác giả Nassim Nicholas Taleb đã đi sâu vào khai thác những sai lầm của tư tưởng cố hữu ấy. Theo ông, “thiên nga đen” là một biến cố tưởng chừng như không thể xảy ra với ba đặc điểm chính: không thể dự đoán, có tác động nặng nề và sau khi nó xảy ra, người ta lại dựng lên một lời giải thích để khiến nó trở nên ít ngẫu nhiên hơn, dễ dự đoán hơn so với bản chất thật của nó. Thành công đáng kinh ngạc của Facebook có thể được coi là một “thiên nga đen”, việc nước Anh rời khỏi Liên minh châu u cũng là một “thiên nga đen”. Thiên nga đen luôn ẩn hiện trong mọi mặt của cuộc sống với những tác động khó lường, theo cả hướng tiêu cực và tích cực.\n" + "\n" + "Tinh tế, táo bạo nhưng không kém phần thú vị, Thiên Nga Đen chắc chắn là cuốn sách không thể bỏ qua cho những ai đam mê hiểu biết. Và cuốn sách này, bản thân nó cũng chính là một thiên nga đen…");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 614);
        values.put("id_nhaxb", 3);
        values.put("id_nhaph", 4);
        db.insert("book", null, values);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.msfm);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Đi tìm lẽ sống");
        values.put("gia ", 71500);
        values.put("id_tacgia ",9);
        values.put("id_danhmuc ", 11);
        values.put("tomtat", "ĐI TÌM LẼ SỐNG CỦA VIKTOR FRANKL LÀ MỘT TRONG NHỮNG QUYỂN SÁCH KINH ĐIỂN CỦA THỜI ĐẠI." +
                "\n" + "\n" + "Thông thường, nếu một quyển sách chỉ có một đoạn văn, một ý tưởng có sức mạnh thay đổi cuộc sống" +
                " của một người, thì chỉ riêng điều đó cũng đã đủ để chúng ta đọc đi đọc lại và dành cho nó một chỗ trên kệ " +
                "sách của mình. Quyển sách này có nhiều đoạn văn như thế.\n" + "\n" + "Trước hết, đây là quyển sách viết về" +
                " sự sinh tồn. Giống như nhiều người Do Thái sinh sống tại Đức và Đông Âu khi ấy, vốn nghĩ rằng mình" +
                " sẽ được an toàn trong những năm 1930, Frankl đã trải qua khoảng thời gian chịu nhiều nghiệt ngã trong" +
                " trại tập trung và trại hủy diệt của Đức quốc xã. Điều kỳ diệu là ông đã sống sót, cụm từ “thép đã tôi " +
                "thế đấy” có thể lột tả chính xác trường hợp này. Nhưng trong Đi tìm lẽ sống, tác giả ít đề cập đến những khó" +
                " nhọc, đau thương, mất mát mà ông đã trải qua, thay vào đó ông viết về những nguồn sức mạnh đã giúp ông tồn tại." +
                "\n" + "\n" + "Ông chua xót kể về những tù nhân đã đầu hàng cuộc sống, mất hết hy vọng ở tương lai và chắc " +
                "hẳn là những người đầu tiên sẽ chết. Ít người chết vì thiếu thức ăn và thuốc men, mà phần lớn họ chết vì thiếu hy vọng, thiếu một lẽ sống. Ngược lại, Frankl đã nuôi giữ hy vọng để giữ cho mình sống sót bằng cách nghĩ về người vợ của mình và trông chờ ngày gặp lại nàng. Ông còn mơ ước sau chiến tranh sẽ được thuyết giảng về các bài học tâm lý ông đã học được từ trại tập trung Auschwitz. Rõ ràng có nhiều tù nhân khao khát được sống đã chết, một số chết vì bệnh, một số chết vì bị hỏa thiêu. Trong tập sách này, Frankl tập trung lý giải nguyên nhân vì sao có những người đã sống sót trong trại tập trung của phát xít Đức hơn là đưa ra lời giải thích cho câu hỏi vì sao phần lớn trong số họ đã không bao giờ trở về nữa.\n" + "\n" + "Nhiệm vụ lớn lao nhất của mỗi người là tìm ra ý nghĩa trong cuộc sống của mình. Frankl đã nhìn thấy ba nguồn ý nghĩa cơ bản của đời người: thành tựu trong công việc, sự quan tâm chăm sóc đối với những người thân yêu và lòng can đảm khi đối mặt với những thời khắc gay go của cuộc sống.\n" + "\n" + "Đau khổ tự bản thân nó không có ý nghĩa gì cả, chính cách phản ứng của chúng ta mới khoác lên cho chúng ý nghĩa.\n" + "\n" + "Frankl đã viết rằng một người “có thể giữ vững lòng quả cảm, phẩm giá và sự bao dung, hoặc người ấy có thể quên mất phẩm giá của con người và tự đặt mình ngang hàng loài cầm thú trong cuộc đấu tranh khắc nghiệt để sinh tồn”. Ông thừa nhận rằng chỉ có một số ít tù nhân của Đức quốc xã là có thể giữ được những phẩm chất ấy, nhưng “chỉ cần một ví dụ như thế cũng đủ chứng minh rằng sức mạnh bên trong của con người có thể đưa người ấy vượt lên số phận nghiệt ngã của mình”.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 214);
        values.put("id_nhaxb", 3);
        values.put("id_nhaph", 1);
        db.insert("book", null, values);
        cursor = database.getData("select * from book");

        while (cursor.moveToNext()) {
            Random random = new Random();
            int randomNumber = random.nextInt(101);
            String sql = "UPDATE book SET luotmua = " + randomNumber + " WHERE id_book = " + cursor.getInt(cursor.getColumnIndex("id_book")) + ";";
            database.QueryData(sql);
//           Toast.makeText(getContext(), cursor.getInt(cursor.getColumnIndex("id_book")) + ": " +
//                   cursor.getInt(cursor.getColumnIndex("luotmua")), Toast.LENGTH_LONG).show();
        }

    }

}
