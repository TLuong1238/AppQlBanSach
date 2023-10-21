package model.Vanh;

public class Donhang {
    private int id_taikhoan, ma_donhang, trangthai;


    public Donhang(int id_taikhoan, int ma_donhang, int trangthai) {
        this.id_taikhoan = id_taikhoan;
        this.ma_donhang = ma_donhang;
        this.trangthai = trangthai;
    }

    public int getId_taikhoan() {
        return id_taikhoan;
    }

    public void setId_taikhoan(int id_taikhoan) {
        this.id_taikhoan = id_taikhoan;
    }

    public int getMa_donhang() {
        return ma_donhang;
    }

    public void setMa_donhang(int ma_donhang) {
        this.ma_donhang = ma_donhang;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
