package org.com.library;

public class Book {
    private int idBook;
    private String bookName;
    private Author idAuthor;
    private Topic topic;

    public Book(int idBook, String bookName, Author idAuthor, Topic topic) {
        this.idBook = idBook;
        this.bookName = bookName;
        this.idAuthor = idAuthor;
        this.topic = topic;
    }

    public Book() {

    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getIdAuthor() {
        return 0;
    }

    public void setIdAuthor(Author idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
