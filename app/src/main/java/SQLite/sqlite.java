package SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlite extends SQLiteOpenHelper {
    public sqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void  QueryData(String sql){
        SQLiteDatabase data = getWritableDatabase();
        data.execSQL(sql);
    }

    public Cursor getData(String sql){
        SQLiteDatabase data = getReadableDatabase();
        return data.rawQuery(sql,null);
    }
    public void updateDB(){
        String table_gioHang = "create table if not exists gio_hang(id_sach INTEGER PRIMARY KEY, id_taikhoan INTEGER null, tensach TEXT, hinhanh BLOB null, soluong INTEGER default 1, gia INTEGER);";
        QueryData(table_gioHang);
    }

    public void createTable(){
        String table_nhaphathanh = "create table if not exists nha_phat_hanh(id_nhaph INTEGER primary key autoincrement, ten_nhaph text not null) ;";
        QueryData(table_nhaphathanh);
        String table_nhaxuatban = "create table if not exists nha_xuatban(id_nhaxb INTEGER primary key autoincrement, ten_nhaxb text not null) ;";
        QueryData(table_nhaxuatban);
        String table_tacgia = "create table if not exists tac_gia(id_tacgia INTEGER primary key autoincrement, ten_tacgia text not null);";
        QueryData(table_tacgia);
        String table_danhmuc = "create table if not exists danh_muc(id_danhmuc INTEGER primary key autoincrement, ten_danhmuc text not null);";
        QueryData(table_danhmuc);
        String table_book = "create table if not exists book(id_book INTEGER primary key autoincrement, tieude text not null, gia INTEGER null default 0, id_tacgia text null," +
                " id_danhmuc text null, tomtat text null, hinhanh BLOB null, danhsach_anh BLOB[] null, ngay_xuatban DATE null, number_of_pages INTEGER null, id_nhaxb INTEGER null, id_nhaph INTEGER null," +
                "FOREIGN KEY (id_nhaxb) REFERENCES nha_xuatban (id_nhaxb)," +
                "FOREIGN KEY (id_nhaph) REFERENCES nha_phat_hanh (id_nhaph)," +
                "FOREIGN KEY (id_tacgia) REFERENCES tac_gia (id_tacgia)," +
                "FOREIGN KEY (id_danhmuc) REFERENCES danh_muc (id_danhmuc));";
        QueryData(table_book);
    }


}
