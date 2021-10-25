package com.example.books;

public class Book {
    private String title;
    private  String subTitle;
    private String  publisher;
    private  String publishedDate;

    public Book(String title, String subTitle, String publisher, String publishedDate) {
        this.title = title;
        this.subTitle = subTitle;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitle() {
        return title;
    }
}
