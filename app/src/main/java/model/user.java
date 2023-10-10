package model;

import java.io.Serializable;

public class user implements Serializable {
    private int id_user;
    private String name_user;
    private String email_user;
    private String password;

    private String sdt;

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public user(int id_user, String name_user, String email_user, String password,String sdt) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.email_user = email_user;
        this.password = password;
        this.sdt = sdt;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
