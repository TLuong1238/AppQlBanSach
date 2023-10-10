package model.Son;

public class DanhMuc {
    private int resoucreImg;
    private String name, id;


    public DanhMuc(int resoucreImg, String name) {
        this.resoucreImg = resoucreImg;
        this.name = name;
    }

    public DanhMuc(int resoucreImg, String name, String id) {
        this.resoucreImg = resoucreImg;
        this.name = name;
        this.id = id;
    }

    public DanhMuc(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public int getResoucreImg() {
        return resoucreImg;
    }

    public void setResoucreImg(int resoucreImg) {
        this.resoucreImg = resoucreImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
