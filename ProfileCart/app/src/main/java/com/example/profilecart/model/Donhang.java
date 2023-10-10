package com.example.profilecart.model;

public class Donhang {
    private int id_giohang, id_taikhoan;
    private String ma_donhang;

    public Donhang(int id_giohang, int id_taikhoan, String ma_donhang) {
        this.id_giohang = id_giohang;
        this.id_taikhoan = id_taikhoan;
        this.ma_donhang = ma_donhang;
    }

    public int getId_giohang() {
        return id_giohang;
    }

    public void setId_giohang(int id_giohang) {
        this.id_giohang = id_giohang;
    }

    public int getId_taikhoan() {
        return id_taikhoan;
    }

    public void setId_taikhoan(int id_taikhoan) {
        this.id_taikhoan = id_taikhoan;
    }

    public String getMa_donhang() {
        return ma_donhang;
    }

    public void setMa_donhang(String ma_donhang) {
        this.ma_donhang = ma_donhang;
    }
}
