package com.example.appsach.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.example.appsach.R;
import com.example.appsach.Category.cateFragment;
import com.example.appsach.databinding.ActivityMainBinding;
import com.example.appsach.Profile.profileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

import SQLite.BitmapUtils;
import SQLite.sqlite;
import model.user;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navHome;
    ActivityMainBinding binding;
    private user newUser;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapping();
        sp = getSharedPreferences("LoginData", MODE_PRIVATE);
        setContentView(binding.getRoot());

        boolean b = sp.getBoolean("back",false);
        if(b)
        {
            replaceFragment(new cateFragment());
        }else replaceFragment(new homeFragment());


        ActionNav();
        newUser = new user(sp.getInt("id", 0), sp.getString("name", ""), sp.getString("email", ""),
                sp.getString("pass", ""), sp.getString("phone", ""));
        boolean i = false;
        if(!i){
            insertSubData();
            i = true;
        }

    }

    private void mapping() {
        navHome = findViewById(R.id.navHome);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
    }

    private void ActionNav() {
        binding.navHome.setOnItemSelectedListener(item ->
        {
            if (item.getItemId() == R.id.itemHome) {
                replaceFragment(new homeFragment());
            }
            if (item.getItemId() == R.id.itemCate) {
                replaceFragment(new cateFragment());
            }
            if (item.getItemId() == R.id.itemProfile) {
                replaceFragment(new profileFragment());
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.constraint_layout, fragment);
        fragmentTransaction.commit();
    }

    public user getNewUser() {
        return newUser;
    }

    @SuppressLint("Range")
    private void insertSubData() {
        sqlite database = new sqlite(MainActivity.this, R.string.databaseName+"",null,1);
        database.createTable();
        database.updateDB();
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

        values.put("id_tacgia", 10);
        values.put("ten_tacgia", "Tổng hợp");
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

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sachconcuu);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Không Phải Sói Nhưng Cũng Đừng Là Cừu");
        values.put("gia ", 92000);
        values.put("id_tacgia ", 9);
        values.put("id_danhmuc ", 11);
        values.put("tomtat", "SÓI VÀ CỪU - BẠN KHÔNG TỐT NHƯ BẠN NGHĨ ĐÂU!\n" + "\n" + "Làn ranh của việc ngây thơ hay xấu xa đôi khi mỏng manh hơn bạn nghĩ.\n" + "\n" + "Bạn làm một việc mà mình cho là đúng, kết quả lại bị mọi người khiển trách.\n" + "\n" + "Bạn ủng hộ một quan điểm của ai đó, và số đông khác lại ủng hộ một quan điểm trái chiều.\n" + "\n" + "Rốt cuộc thì bạn sai hay họ sai?\n" + "\n" + "Cuốn sách “Không phải sói nhưng cũng đừng là cừu” đến từ tác giả Lê Bảo Ngọc sẽ giúp bạn hiểu rõ hơn những khía cạnh sắc sảo phía sau những nhận định đúng, sai đơn thuần của mỗi người.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 296);
        values.put("id_nhaxb", 5);
        values.put("id_nhaph", 5);
        db.insert("book", null, values);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bangayhanhphuc);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Ba Ngày Hạnh Phúc");
        values.put("gia ", 85000);
        values.put("id_tacgia ", 9);
        values.put("id_danhmuc ", 13);
        values.put("tomtat", "Thật vô vọng khi thích một người không còn trên thế gian này.\n" + "\n" + "Xem ra từ nay về sau chẳng có một điều tốt lành nào đến với cuộc đời tôi. Chính vì thế mà mỗi năm sinh mệnh của tôi chỉ đáng giá 10.000 yên. Quá bi quan về tương lai, tôi đã bán đi gần hết sinh mệnh của mình. Dù có cố làm gì để được hạnh phúc trong quãng đời ngắn ngủi còn lại, thì tôi cũng chỉ nhận được kết quả trái ngược. Trong khi tôi đang tiếp tục sống vô định thì “người giám sát” Miyagi vẫn đăm đăm nhìn tôi với ánh mắt điềm tĩnh.\n" + "\n" + "Tôi đã mất thêm hai tháng cuộc đời để nhận ra rằng tôi hạnh phúc nhất khi sống vì cô ấy.\n" + "\n" + "Tập truyện nổi tiếng trên web này, cuối cùng cũng được xuất bản!\n" + "\n" + "(Tên ban đầu của nó là Tôi đã bán sinh mệnh của mình. Mỗi năm 10.000 yên.)\n" + "\n" + "Giá sản phẩm trên Tiki đã bao gồm thuế theo luật hiện hành. Bên cạnh đó, tuỳ vào loại sản phẩm, hình thức và địa chỉ giao hàng mà có thể phát sinh thêm chi phí khác như phí vận chuyển, phụ phí hàng cồng kềnh, thuế nhập khẩu (đối với đơn hàng giao từ nước ngoài có giá trị trên 1 triệu đồng).....");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 279);
        values.put("id_nhaxb", 4);
        values.put("id_nhaph", 6);
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

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sach6);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Tư duy nhanh và chậm");
        values.put("gia ", 29000);
        values.put("id_tacgia ",5);
        values.put("id_danhmuc ", 2);
        values.put("tomtat", "Bạn nghĩ rằng bạn tư duy nhanh, hay chậm? Bạn tư duy và suy nghĩ theo lối “trông mặt bắt hình dong”, đánh giá mọi vật nhanh chóng bằng cảm quan, quyết định dựa theo cảm xúc hay tư duy một cách cẩn thận, chậm rãi nhưng logic hợp lý về một vấn đề. Tư duy nhanh và chậm sẽ đưa ra và lý giải hai hệ thống tư duy tác động đến con đường nhận thức của bạn. Kahneman gọi đó là: hệ thống 1 và hệ thống 2. Hệ thống 1, còn gọi là cơ chế nghĩ nhanh, tự động, thường xuyên được sử dụng, cảm tính, rập khuôn và tiềm thức. Hệ thống 2, còn gọi là cơ chế nghĩ chậm, đòi hỏi ít nỗ lực, ít được sử dụng, dùng logic có tính toán và ý thức. Trong một loạt thí nghiệm tâm lý mang tính tiên phong, Kahneman và Tversky chứng minh rằng, con người chúng ta thường đi đến quyết định theo cơ chế nghĩ nhanh hơn là ghĩ chậm. Phần lớn nội dung của cuốn sách chỉ ra những sai lầm của con người khi suy nghĩ theo hệ thống 1. Kahneman chứng minh rằng chúng ta tệ hơn những gì chúng ta tưởng: đó là chúng ta không biết những gì chúng ta không biết!\n" +
                "Cuốn sách đặc biệt đã dành được vô số giải thưởng danh giá: Sách khoa học hay nhất của Học viện Khoa học Quốc gia năm 2012, được tạp chí The New York Times bình chọn là sách hay nhất năm 2011, một trong những cuốn sách kinh tế xuất sắc năm 2011, chiến thắng giải thưởng cuốn sách được quan tâm nhất năm 2011 của tạp chí Los Algeles… Tư duy nhanh và chậm đáp ứng hai tiêu chí của một cuốn sách hay, thứ nhất nó thách thức quan điểm của người đọc, thứ hai, nó không phải là những trang sách với những con chữ khô cứng mà nó vô cùng vui nhộn và hấp dẫn. Không nghi ngờ gì nữa, đây là cuốn sách hàn lâm dành cho tất cả mọi người!");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 612);
        values.put("id_nhaxb", 6);
        values.put("id_nhaph", 4);
        db.insert("book", null, values);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sach7);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Rừng nauy");
        values.put("gia ", 290000);
        values.put("id_tacgia ",4);
        values.put("id_danhmuc ", 1);
        values.put("tomtat", "Câu chuyện bắt đầu từ một chuyến bay trong ngày mưa ảm đạm, một người đàn ông 37 tuổi chợt nghe thấy bài hát gắn liền với hình ảnh người yêu cũ, thế là quá khứ ùa về xâm chiếm thực tại. Mười tám năm trước, người đàn ông ấy là chàng Toru Wanatabe trẻ trung, mỗi chủ nhật lại cùng nàng Naoko lang thang vô định trên những con phố Tokyo. Họ sánh bước bên nhau để thấy mình còn sống, còn tồn tại, và gắng gượng tiếp tục sống, tiếp tục tồn tại sau cái chết của người bạn cũ Kizuki. Cho đến khi Toru nhận ra rằng mình thực sự yêu và cần có Naoko thì cũng là lúc nàng không thể chạy trốn những ám ảnh quá khứ, không thể hòa nhập với cuộc sống thực tại và trở về dưỡng bệnh trong một khu trị liệu khép kín. Toru, bên cạnh giảng đường vô nghĩa chán ngắt, bên cạnh những đêm chơi bời chuyển từ cảm giác thích thú đến uể oải, ghê tởẫn kiên nhẫn chờ đợi và hy vọng vào sự hồi phục của Naoko. Cuối cùng, những lá thư, những lần thăm hỏi, hồi ức về lần ân ái duy nhất của Toru không thể níu Naoko ở lại, nàng chọn cái chết như một lối đi thanh thản. Từ trong mất mát, Toru nhận ra rằng mình cần tiếp tục sống và bắt đầu tình yêu mới với Midori.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 634);
        values.put("id_nhaxb", 3);
        values.put("id_nhaph", 2);
        db.insert("book", null, values);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sach7);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Rừng nauy");
        values.put("gia ", 290000);
        values.put("id_tacgia ",4);
        values.put("id_danhmuc ", 1);
        values.put("tomtat", "Câu chuyện bắt đầu từ một chuyến bay trong ngày mưa ảm đạm, một người đàn ông 37 tuổi chợt nghe thấy bài hát gắn liền với hình ảnh người yêu cũ, thế là quá khứ ùa về xâm chiếm thực tại. Mười tám năm trước, người đàn ông ấy là chàng Toru Wanatabe trẻ trung, mỗi chủ nhật lại cùng nàng Naoko lang thang vô định trên những con phố Tokyo. Họ sánh bước bên nhau để thấy mình còn sống, còn tồn tại, và gắng gượng tiếp tục sống, tiếp tục tồn tại sau cái chết của người bạn cũ Kizuki. Cho đến khi Toru nhận ra rằng mình thực sự yêu và cần có Naoko thì cũng là lúc nàng không thể chạy trốn những ám ảnh quá khứ, không thể hòa nhập với cuộc sống thực tại và trở về dưỡng bệnh trong một khu trị liệu khép kín. Toru, bên cạnh giảng đường vô nghĩa chán ngắt, bên cạnh những đêm chơi bời chuyển từ cảm giác thích thú đến uể oải, ghê tởẫn kiên nhẫn chờ đợi và hy vọng vào sự hồi phục của Naoko. Cuối cùng, những lá thư, những lần thăm hỏi, hồi ức về lần ân ái duy nhất của Toru không thể níu Naoko ở lại, nàng chọn cái chết như một lối đi thanh thản. Từ trong mất mát, Toru nhận ra rằng mình cần tiếp tục sống và bắt đầu tình yêu mới với Midori.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 634);
        values.put("id_nhaxb", 3);
        values.put("id_nhaph", 2);
        db.insert("book", null, values);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stn1);
        bytes = BitmapUtils.getByte(bitmap);

        values.put("tieude", "Sách cho bé tập 1");
        values.put("gia ", 120000);
        values.put("id_tacgia ",10);
        values.put("id_danhmuc ", 3);
        values.put("tomtat","TƯ DUY (3~4 tuổi)\n" +
                "\n" +
                "Tủ sách thiếu nhi Nhật Bản dành chobes từ 3 đến 4 tuổi \n" +
                "\n" +
                "Thích hợp cho trẻ bắt đầu muốn làm thử nhiều việc, kích thích trẻ phát triển tư duy\n" +
                "\n" +
                "☆ Khi bé bắt đầu khám phá thế giới, một kỹ năng rất quan trọng mà ba mẹ nên chú ý đó là rèn luyện kỹ năng tư duy cho con. Vì khả năng tư duy sẽ giúp trẻ hình thành nhiều khả năng thực sự như học hỏi, tư duy, sáng tạo và giải quyết vấn đề sẽ kích thích trẻ phát triển trí nhớ, khả năng phán đoán, suy luận và thậm chí cả nhận thức của trẻ.\n" +
                "\n" +
                "\n" +
                "☆ Đối với trẻ 3~4 tuổi thì những việc mà trẻ có thể làm và muốn thử làm được tăng lên nhiều hơn so với trẻ 2~3 tuổi. Trẻ được bồi dưỡng năng lực suy luận khi tự mình suy nghĩ tìm câu trả lời với những dạng bài tìm hình hay trò chơi mê cung. Ngoài ra, việc vui chơi với những bài tập thủ công sử dụng keo dán và kéo có trong sách cũng khơi gợi húng thú học tập của trẻ, giúp trẻ vừa học vui vừa nâng cao trí tuệ mỗi ngày.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 123);
        values.put("id_nhaxb", 1);
        values.put("id_nhaph", 5);
        db.insert("book", null, values);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stn2);
        bytes = BitmapUtils.getByte(bitmap);
        values.put("tieude", "Sách cho bé tập 2");
        values.put("gia ", 112000);
        values.put("id_tacgia ",10);
        values.put("id_danhmuc ", 3);
        values.put("tomtat","TƯ DUY (3~4 tuổi)\n" +
                "\n" +
                "Tủ sách thiếu nhi Nhật Bản dành chobes từ 3 đến 4 tuổi \n" +
                "\n" +
                "Thích hợp cho trẻ bắt đầu muốn làm thử nhiều việc, kích thích trẻ phát triển tư duy\n" +
                "\n" +
                "☆ Khi bé bắt đầu khám phá thế giới, một kỹ năng rất quan trọng mà ba mẹ nên chú ý đó là rèn luyện kỹ năng tư duy cho con. Vì khả năng tư duy sẽ giúp trẻ hình thành nhiều khả năng thực sự như học hỏi, tư duy, sáng tạo và giải quyết vấn đề sẽ kích thích trẻ phát triển trí nhớ, khả năng phán đoán, suy luận và thậm chí cả nhận thức của trẻ.\n" +
                "\n" +
                "\n" +
                "☆ Đối với trẻ 3~4 tuổi thì những việc mà trẻ có thể làm và muốn thử làm được tăng lên nhiều hơn so với trẻ 2~3 tuổi. Trẻ được bồi dưỡng năng lực suy luận khi tự mình suy nghĩ tìm câu trả lời với những dạng bài tìm hình hay trò chơi mê cung. Ngoài ra, việc vui chơi với những bài tập thủ công sử dụng keo dán và kéo có trong sách cũng khơi gợi húng thú học tập của trẻ, giúp trẻ vừa học vui vừa nâng cao trí tuệ mỗi ngày.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 143);
        values.put("id_nhaxb", 1);
        values.put("id_nhaph", 5);
        db.insert("book", null, values);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stn3);
        bytes = BitmapUtils.getByte(bitmap);
        values.put("tieude", "Sách cho bé tập 3");
        values.put("gia ", 12200);
        values.put("id_tacgia ",10);
        values.put("id_danhmuc ", 3);
        values.put("tomtat","TƯ DUY (3~4 tuổi)\n" +
                "\n" +
                "Tủ sách thiếu nhi Nhật Bản dành chobes từ 3 đến 4 tuổi \n" +
                "\n" +
                "Thích hợp cho trẻ bắt đầu muốn làm thử nhiều việc, kích thích trẻ phát triển tư duy\n" +
                "\n" +
                "☆ Khi bé bắt đầu khám phá thế giới, một kỹ năng rất quan trọng mà ba mẹ nên chú ý đó là rèn luyện kỹ năng tư duy cho con. Vì khả năng tư duy sẽ giúp trẻ hình thành nhiều khả năng thực sự như học hỏi, tư duy, sáng tạo và giải quyết vấn đề sẽ kích thích trẻ phát triển trí nhớ, khả năng phán đoán, suy luận và thậm chí cả nhận thức của trẻ.\n" +
                "\n" +
                "\n" +
                "☆ Đối với trẻ 3~4 tuổi thì những việc mà trẻ có thể làm và muốn thử làm được tăng lên nhiều hơn so với trẻ 2~3 tuổi. Trẻ được bồi dưỡng năng lực suy luận khi tự mình suy nghĩ tìm câu trả lời với những dạng bài tìm hình hay trò chơi mê cung. Ngoài ra, việc vui chơi với những bài tập thủ công sử dụng keo dán và kéo có trong sách cũng khơi gợi húng thú học tập của trẻ, giúp trẻ vừa học vui vừa nâng cao trí tuệ mỗi ngày.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 120);
        values.put("id_nhaxb", 1);
        values.put("id_nhaph", 5);
        db.insert("book", null, values);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.stn3);
        bytes = BitmapUtils.getByte(bitmap);
        values.put("tieude", "Sách cho bé tập 3");
        values.put("gia ", 12200);
        values.put("id_tacgia ",10);
        values.put("id_danhmuc ", 3);
        values.put("tomtat","TƯ DUY (3~4 tuổi)\n" +
                "\n" +
                "Tủ sách thiếu nhi Nhật Bản dành chobes từ 3 đến 4 tuổi \n" +
                "\n" +
                "Thích hợp cho trẻ bắt đầu muốn làm thử nhiều việc, kích thích trẻ phát triển tư duy\n" +
                "\n" +
                "☆ Khi bé bắt đầu khám phá thế giới, một kỹ năng rất quan trọng mà ba mẹ nên chú ý đó là rèn luyện kỹ năng tư duy cho con. Vì khả năng tư duy sẽ giúp trẻ hình thành nhiều khả năng thực sự như học hỏi, tư duy, sáng tạo và giải quyết vấn đề sẽ kích thích trẻ phát triển trí nhớ, khả năng phán đoán, suy luận và thậm chí cả nhận thức của trẻ.\n" +
                "\n" +
                "\n" +
                "☆ Đối với trẻ 3~4 tuổi thì những việc mà trẻ có thể làm và muốn thử làm được tăng lên nhiều hơn so với trẻ 2~3 tuổi. Trẻ được bồi dưỡng năng lực suy luận khi tự mình suy nghĩ tìm câu trả lời với những dạng bài tìm hình hay trò chơi mê cung. Ngoài ra, việc vui chơi với những bài tập thủ công sử dụng keo dán và kéo có trong sách cũng khơi gợi húng thú học tập của trẻ, giúp trẻ vừa học vui vừa nâng cao trí tuệ mỗi ngày.");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 120);
        values.put("id_nhaxb", 1);
        values.put("id_nhaph", 5);
        db.insert("book", null, values);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kns1);
        bytes = BitmapUtils.getByte(bitmap);
        values.put("tieude", "101 Câu Chuyện Học Sinh Cần Đọc Để Giúp Các Em Trưởng Thành");
        values.put("gia ", 34000);
        values.put("id_tacgia ",10);
        values.put("id_danhmuc ", 4);
        values.put("tomtat","Bộ sách Kỹ năng sống dành cho học sinh - 101 câu chuyện học sinh cần đọc chọn lọc những câu chuyện ý nghĩa, giúp các em hình thành và bồi dưỡng những phẩm chất đạo đức tốt đẹp: tự tin, lạc quan, nhân ái, kiên trì, biết sống khát vọ Lời kể ngắn gọn, sinh động, cuối mỗi truyện còn có mục tổng kết \"Bài học lớn sau câu chuyện nhỏ\" để các em thêm thấm nhuần giá trị lớn lao trong từng câu chuyện.\n" +
                "\n" +
                "\"Ngày cuối năm, người con thứ ba từ sớm đã ra chợ buôn bán, đến khi trời tối mịt mới về nhà. Anh thấy vợ đã nấu xong các món liền bảo vợ mang đồ ăn sang biếu cha mẹ. Nhưng vợ anh càu nhàu nói rằng, anh không phải con ruột, cũng chẳng được chia của cải gì nên không cần phải đi. Người chồng nghe những lời vợ nói, trong lòng rất buồn. Anh nói với vợ, mình có ngày hôm nay là nhờ ơn nuôi dưỡng của cha mẹ, làm người không được vong ân bội nghĩa. Nói xong, anh bưng những đồ ăn gnon nhất sang nhà cha mẹ.\"");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 190);
        values.put("id_nhaxb", 5);
        values.put("id_nhaph", 2);
        db.insert("book", null, values);


        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.yhoc1);
        bytes = BitmapUtils.getByte(bitmap);
        values.put("tieude", "Trung Y Học Khái Luận");
        values.put("gia ", 467000);
        values.put("id_tacgia ",1);
        values.put("id_danhmuc ", 5);
        values.put("tomtat","Y học Trung Quốc là một kho tàng quý báu vĩ đại, là tổng kết kinh nghiệm của nhân dân Trung Quốc đấu tranh với bệnh tật mấy nghìn năm lại đây, dưới sự chỉ đạo của hệ thống lý luận độc đáo đó, đã định ra được nguyên tắc chữa bệnh bằng “Biện chứng luận trị” về phương diện kỹ thuật chữa bệnh, ngoài việc dùng thuốc ra còn có nhiều cách chữa đặc biệt khác nhau như: châm cứu, khí công, xoa bóp, … Ngoài ra các mặt có quan hệ đến việc quản lý y dược, việc giáo dục y học cũng đều đã hình thành được một chế độ tương đối đầy đủ. ");
        values.put("hinhanh", bytes);
        values.put("number_of_pages", 289);
        values.put("id_nhaxb", 6);
        values.put("id_nhaph", 7);
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
        db.close();
        cursor.close();
        database.close();

    }
}