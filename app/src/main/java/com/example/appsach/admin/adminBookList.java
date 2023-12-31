package com.example.appsach.admin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.example.appsach.R;
import java.util.ArrayList;
import java.util.Random;
import SQLite.BitmapUtils;
import SQLite.sqlite;
import adapter.Son.AdminArrayAdapter;
import model.Son.SubDataItem;

public class adminBookList extends Activity {
    private ImageView backAdmin, searchingBook;

    private EditText inputSearching;

    private ListView bookList;

    private Button addingBook;

    private sqlite database;

    private AdminArrayAdapter arrayAdapter;
    private ArrayList<SubDataItem> arrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_book_list);
        database = new sqlite(this, R.string.databaseName + "", null, 1);
        database.createTable();
        //insertSubData();
        anhxa();
        arrayList = new ArrayList<>();

        arrayAdapter = new AdminArrayAdapter(this, R.layout.admin_array_custom, arrayList);
        update();
        arrayAdapter.getInforTable("book", "id_book", "tieude");
        bookList.setAdapter(arrayAdapter);

        //backAdmin
        backAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminBookList.this, MainAdmin.class));
                finish();
            }
        });

        //btn adding book
        addingBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminBookList.this, adminXulyBook.class);
                Bundle bundle = new Bundle();
                bundle.putString("request", "adding");
                intent.putExtra("pack", bundle);
                startActivity(intent);
            }
        });

        //xu ly sua thong tin sach

        bookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(adminBookList.this, adminXulyBook.class);
                Bundle bundle = new Bundle();
                bundle.putString("request", "changeInfo");
                bundle.putInt("id", arrayList.get(i).getId());
                intent.putExtra("pack", bundle);
                startActivity(intent);
            }
        });

        //xu ly tim kiem
        searchingBook.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                arrayList.clear();
                String key = inputSearching.getText().toString();
                Cursor c = database.getData("Select * from book where tieude like '%" + key + "%'");
                while (c.moveToNext()) {
                    arrayList.add(new SubDataItem(c.getInt(c.getColumnIndex("id_book")), c.getString(c.getColumnIndex("tieude"))));
                }
                arrayAdapter.notifyDataSetChanged();
                c.close();
            }
        });

    }

    @SuppressLint("Range")
    private void update() {
        arrayList.clear();
        Cursor c = database.getData("SELECT * FROM book");
        while (c.moveToNext()) {
            arrayList.add(new SubDataItem(c.getInt(c.getColumnIndex("id_book")), c.getString(c.getColumnIndex("tieude"))));
        }
        arrayAdapter.notifyDataSetChanged();
        c.close();
    }

    private void anhxa() {
        backAdmin = findViewById(R.id.backDMAdmin);
        searchingBook = findViewById(R.id.img_adSearch);
        inputSearching = findViewById(R.id.ed_adSearchBook);
        bookList = findViewById(R.id.danhsach_book);
        addingBook = findViewById(R.id.add_book);
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
        values.put("tomtat", "Hãy làm quen với Zezé, cậu bé tinh nghịch siêu hạng đồng thời cũng đáng yêu bậc nhất, với ước mơ lớn lên trở thành nhà thơ cổ thắt nơ bướm. Chẳng phải ai cũng công nhận khoản “đáng yêu” kia đâu nhé. Bởi vì, ở cái xóm ngoại ô nghèo ấy, nỗi khắc khổ bủa vây đã che mờ mắt người ta trước trái tim thiện lương cùng trí tưởng tượng tuyệt vời của cậu bé con năm tuổi.\n" +
                "\n" +
                "Có hề gì đâu bao nhiêu là hắt hủi, đánh mắng, vì Zezé đã có một người bạn đặc biệt để trút nỗi lòng: cây cam ngọt nơi vườn sau. Và cả một người bạn nữa, bằng xương bằng thịt, một ngày kia xuất hiện, cho cậu bé nhạy cảm khôn sớm biết thế nào là trìu mến, thế nào là nỗi đau, và mãi mãi thay đổi cuộc đời cậu.\n" +
                "Mở đầu bằng những thanh âm trong sáng và kết thúc lắng lại trong những nốt trầm hoài niệm, Cây cam ngọt của tôi khiến ta nhận ra vẻ đẹp thực sự của cuộc sống đến từ những điều giản dị như bông hoa trắng của cái cây sau nhà, và rằng cuộc đời thật khốn khổ nếu thiếu đi lòng yêu thương và niềm trắc ẩn. Cuốn sách kinh điển này bởi thế không ngừng khiến trái tim người đọc khắp thế giới thổn thức, kể từ khi ra mắt lần đầu năm 1968 tại Brazil.");
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
        values.put("tomtat", "Thiên Nga Đen\n" +
                "\n" +
                "Trước khi khám phá ra thiên nga đen tồn tại trên đời (ở Úc), người ta vẫn tin rằng tất cả chim thiên nga trên đời đều có màu trắng. Phát hiện bất ngờ này đã thay đổi toàn bộ thế giới quan của nhân loại (về thiên nga).\n" +
                "\n" +
                "Chúng ta không biết rất nhiều thứ nhưng lại hành động như thể mình có khả năng dự đoán được mọi điều. Và trong cuốn sách này, tác giả Nassim Nicholas Taleb đã đi sâu vào khai thác những sai lầm của tư tưởng cố hữu ấy. Theo ông, “thiên nga đen” là một biến cố tưởng chừng như không thể xảy ra với ba đặc điểm chính: không thể dự đoán, có tác động nặng nề và sau khi nó xảy ra, người ta lại dựng lên một lời giải thích để khiến nó trở nên ít ngẫu nhiên hơn, dễ dự đoán hơn so với bản chất thật của nó. Thành công đáng kinh ngạc của Facebook có thể được coi là một “thiên nga đen”, việc nước Anh rời khỏi Liên minh châu u cũng là một “thiên nga đen”. Thiên nga đen luôn ẩn hiện trong mọi mặt của cuộc sống với những tác động khó lường, theo cả hướng tiêu cực và tích cực.\n" +
                "\n" +
                "Tinh tế, táo bạo nhưng không kém phần thú vị, Thiên Nga Đen chắc chắn là cuốn sách không thể bỏ qua cho những ai đam mê hiểu biết. Và cuốn sách này, bản thân nó cũng chính là một thiên nga đen…");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 614);
        values.put("id_nhaxb", 3);
        values.put("id_nhaph", 4);
        db.insert("book", null, values);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.msfm);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Đi tìm lẽ sống");
        values.put("gia ", 71500);
        values.put("id_tacgia ", 9);
        values.put("id_danhmuc ", 11);
        values.put("tomtat", "ĐI TÌM LẼ SỐNG CỦA VIKTOR FRANKL LÀ MỘT TRONG NHỮNG QUYỂN SÁCH KINH ĐIỂN CỦA THỜI ĐẠI.\n" +
                "\n" +
                "Thông thường, nếu một quyển sách chỉ có một đoạn văn, một ý tưởng có sức mạnh thay đổi cuộc sống của một người, thì chỉ riêng điều đó cũng đã đủ để chúng ta đọc đi đọc lại và dành cho nó một chỗ trên kệ sách của mình. Quyển sách này có nhiều đoạn văn như thế.\n" +
                "\n" +
                "Trước hết, đây là quyển sách viết về sự sinh tồn. Giống như nhiều người Do Thái sinh sống tại Đức và Đông Âu khi ấy, vốn nghĩ rằng mình sẽ được an toàn trong những năm 1930, Frankl đã trải qua khoảng thời gian chịu nhiều nghiệt ngã trong trại tập trung và trại hủy diệt của Đức quốc xã. Điều kỳ diệu là ông đã sống sót, cụm từ “thép đã tôi thế đấy” có thể lột tả chính xác trường hợp này. Nhưng trong Đi tìm lẽ sống, tác giả ít đề cập đến những khó nhọc, đau thương, mất mát mà ông đã trải qua, thay vào đó ông viết về những nguồn sức mạnh đã giúp ông tồn tại.\n" +
                "\n" +
                "Ông chua xót kể về những tù nhân đã đầu hàng cuộc sống, mất hết hy vọng ở tương lai và chắc hẳn là những người đầu tiên sẽ chết. Ít người chết vì thiếu thức ăn và thuốc men, mà phần lớn họ chết vì thiếu hy vọng, thiếu một lẽ sống. Ngược lại, Frankl đã nuôi giữ hy vọng để giữ cho mình sống sót bằng cách nghĩ về người vợ của mình và trông chờ ngày gặp lại nàng. Ông còn mơ ước sau chiến tranh sẽ được thuyết giảng về các bài học tâm lý ông đã học được từ trại tập trung Auschwitz. Rõ ràng có nhiều tù nhân khao khát được sống đã chết, một số chết vì bệnh, một số chết vì bị hỏa thiêu. Trong tập sách này, Frankl tập trung lý giải nguyên nhân vì sao có những người đã sống sót trong trại tập trung của phát xít Đức hơn là đưa ra lời giải thích cho câu hỏi vì sao phần lớn trong số họ đã không bao giờ trở về nữa.\n" +
                "\n" +
                "Nhiệm vụ lớn lao nhất của mỗi người là tìm ra ý nghĩa trong cuộc sống của mình. Frankl đã nhìn thấy ba nguồn ý nghĩa cơ bản của đời người: thành tựu trong công việc, sự quan tâm chăm sóc đối với những người thân yêu và lòng can đảm khi đối mặt với những thời khắc gay go của cuộc sống.\n" +
                "\n" +
                "Đau khổ tự bản thân nó không có ý nghĩa gì cả, chính cách phản ứng của chúng ta mới khoác lên cho chúng ý nghĩa.\n" +
                "\n" +
                "Frankl đã viết rằng một người “có thể giữ vững lòng quả cảm, phẩm giá và sự bao dung, hoặc người ấy có thể quên mất phẩm giá của con người và tự đặt mình ngang hàng loài cầm thú trong cuộc đấu tranh khắc nghiệt để sinh tồn”. Ông thừa nhận rằng chỉ có một số ít tù nhân của Đức quốc xã là có thể giữ được những phẩm chất ấy, nhưng “chỉ cần một ví dụ như thế cũng đủ chứng minh rằng sức mạnh bên trong của con người có thể đưa người ấy vượt lên số phận nghiệt ngã của mình”.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 214);
        values.put("id_nhaxb", 3);
        values.put("id_nhaph", 1);
        db.insert("book", null, values);
        Cursor cursor = database.getData("select * from book");

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
