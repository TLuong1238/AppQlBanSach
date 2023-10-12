package model.Son;

import android.graphics.Bitmap;

public class Item {
    private int id, luotmua;

    public Item(String name, Bitmap image) {
        this.name = name;
        this.image = image;
    }

    public Item(int id, String name, String gia, String danhMuc, String nhaXuatBan, String nhaPhatHanh, Bitmap image) {
        this.id = id;
        this.name = name;
        this.gia = gia;
        this.danhMuc = danhMuc;
        this.nhaXuatBan = nhaXuatBan;
        this.nhaPhatHanh = nhaPhatHanh;
        this.image = image;
    }

    private String name;
    private String gia;

    public Item(String name, String gia, String tomTat, String tacGia, String danhMuc, String nhaXuatBan, String nhaPhatHanh, int numberOfPages, Bitmap image) {
        this.name = name;
        this.gia = gia;
        this.tomTat = tomTat;
        this.tacGia = tacGia;
        this.danhMuc = danhMuc;
        this.nhaXuatBan = nhaXuatBan;
        this.nhaPhatHanh = nhaPhatHanh;
        this.numberOfPages = numberOfPages;
        this.image = image;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(String danhMuc) {
        this.danhMuc = danhMuc;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getNhaPhatHanh() {
        return nhaPhatHanh;
    }

    public void setNhaPhatHanh(String nhaPhatHanh) {
        this.nhaPhatHanh = nhaPhatHanh;
    }

    private String tomTat;
    private String tacGia;
    private String danhMuc;
    private String nhaXuatBan;
    private String nhaPhatHanh;

    private int numberOfPages;

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Item(String name, String gia, int numberOfPages, Bitmap image) {
        this.name = name;
        this.gia = gia;
        this.numberOfPages = numberOfPages;
        this.image = image;
    }

    private Bitmap image;

    public Item(String name, String gia, Bitmap image) {
        this.name = name;
        this.gia = gia;
        this.image = image;
    }

    public Item(String name, String gia, String img) {
        this.name = name;
        this.gia = gia;
    }


    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
