package org.com.library;

public class Author {
    private int idAuthor;
    private String AuthorName;
    private String gender;

    public Author(int idAuthor, String authorName, String gender) {
        this.idAuthor = idAuthor;
        AuthorName = authorName;
        this.gender = gender;
    }

    public Author() {

    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static Author valueOf(int idAuthor) {
        return null;
    }
}
