package com.phungthanhquan.bookapp.Object;

public class ItemBook {
    private String title;
    private String urlImage;
    private String bookID;

    public ItemBook(String title, String urlImage, String bookID) {
        this.title = title;
        this.urlImage = urlImage;
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }
}
