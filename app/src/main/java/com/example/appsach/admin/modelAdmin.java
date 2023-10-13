package com.example.appsach.admin;

import java.io.Serializable;

public class modelAdmin implements Serializable {
    private String nameAdmin,passAdmin,emailAdmin;

    public modelAdmin() {
    }

    public modelAdmin(String nameAdmin, String passAdmin, String emailAdmin) {
        this.nameAdmin = nameAdmin;
        this.passAdmin = passAdmin;
        this.emailAdmin = emailAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        this.nameAdmin = nameAdmin;
    }

    public String getPassAdmin() {
        return passAdmin;
    }

    public void setPassAdmin(String passAdmin) {
        this.passAdmin = passAdmin;
    }
}
