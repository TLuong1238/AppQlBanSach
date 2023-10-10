package com.application.test_baitaplon.Model;

public class Item {
    private int resoucreImage;
    private String name, gia;

    public Item(int resoucreImage, String name, String gia) {
        this.resoucreImage = resoucreImage;
        this.name = name;
        this.gia = gia;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public Item(int resoucreImage, String name) {
        this.resoucreImage = resoucreImage;
        this.name = name;
    }

    public int getResoucreImage() {
        return resoucreImage;
    }

    public void setResoucreImage(int resoucreImage) {
        this.resoucreImage = resoucreImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
