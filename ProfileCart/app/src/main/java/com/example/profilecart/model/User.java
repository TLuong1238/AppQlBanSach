package com.example.profilecart.model;

public class User {
    private int id_taikhoan;
    private String email, tentaikhoan, matkhau;

    public User(int id_taikhoan, String email, String tentaikhoan, String matkhau) {
        this.id_taikhoan = id_taikhoan;
        this.email = email;
        this.tentaikhoan = tentaikhoan;
        this.matkhau = matkhau;
    }

    public int getId_taikhoan() {
        return id_taikhoan;
    }

    public void setId_taikhoan(int id_taikhoan) {
        this.id_taikhoan = id_taikhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
}
