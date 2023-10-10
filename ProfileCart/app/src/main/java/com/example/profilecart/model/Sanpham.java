package com.example.profilecart.model;

public class Sanpham {
    public int id_sanpham;
    public String ten_sanpham;
    public long giasp;
    public String hinhanh;
    public int soluong;

    public Sanpham(int id_sanpham, String ten_sanpham, long giasp, String hinhanh, int soluong) {
        this.id_sanpham = id_sanpham;
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

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
