package com.phungthanhquan.bookapp.Model.Activity;

import com.phungthanhquan.bookapp.Object.Book;

import java.util.List;

public class ModelActivityDetailBook {
    private Book books;
    public Book getBook(){
        books = new Book("1","https://webtruyen.com/public/images/toithayhoavangtrencoxanh1woCMXi6Ln.jpg"
                ,"Tôi thấy hoa vàng trên cỏ xanh"
                ,"Phùng Thanh Quân"
                ,"Lao Động"
                ,139
                ,"20-11-1996"
                ,1500000,3
                ,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
        ,257);
        return books;
    }
}
