package model.Vanh;

import android.graphics.Bitmap;

public class ItemGiohang {
    public int id_sanpham;
    public int id_taikhoan;
    public String ten_sanpham;
    public long giasp;
    public Bitmap hinhanh;
    public int soluong;

    public ItemGiohang(int id_sanpham, int id_taikhoan, String ten_sanpham, long giasp, Bitmap hinhanh, int soluong) {
        this.id_sanpham = id_sanpham;
        this.id_taikhoan = id_taikhoan;
        this.ten_sanpham = ten_sanpham;
        this.giasp = giasp;
        this.hinhanh = hinhanh;
        this.soluong = soluong;
    }

    public int getId_sanpham() {
        return id_sanpham;
    }

    public void setId_sanpham(int id_sanpham) {
        this.id_sanpham = id_sanpham;
    }

    public int getId_taikhoan() {
        return id_taikhoan;
    }

    public void setId_taikhoan(int id_taikhoan) {
        this.id_taikhoan = id_taikhoan;
    }

    public String getTen_sanpham() {
        return ten_sanpham;
    }

    public void setTen_sanpham(String ten_sanpham) {
        this.ten_sanpham = ten_sanpham;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public Bitmap getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(Bitmap hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
