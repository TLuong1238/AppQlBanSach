package model;

public class item {
    private int resoucreImage;
    private String name, gia;


    public item(int resoucreImage, String name, String gia) {
        this.resoucreImage = resoucreImage;
        this.name = name;
        this.gia = gia;
    }
    public item(int resoucreImage, String name) {
        this.resoucreImage = resoucreImage;
        this.name = name;
    }
//fix later
    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
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
