package model;

import java.util.List;

public class category {
    private String nameCate;
    private List<Book> book;

    public category(String nameCate, List<Book> book) {
        this.nameCate = nameCate;
        this.book = book;
    }

    public String getNameCate() {
        return nameCate;
    }

    public void setNameCate(String nameCate) {
        this.nameCate = nameCate;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
