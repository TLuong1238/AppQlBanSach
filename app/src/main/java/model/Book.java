package model;

import java.io.Serializable;

public class Book implements Serializable {
    private int sourceId;
    private String title;

    public Book(int sourceId, String title) {
        this.sourceId = sourceId;
        this.title = title;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
