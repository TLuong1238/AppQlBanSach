package model;

import java.util.ArrayList;
import java.util.List;

import model.Son.Item;

public class category {
    private String nameCate;
    private ArrayList<Item> item;

    public category(String nameCate, ArrayList<Item> book) {
        this.nameCate = nameCate;
        this.item = book;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public ArrayList<Item> getItem() {
        return item;
    }

    public void setBook(ArrayList<Item> book) {
        this.item = book;
    }
}
