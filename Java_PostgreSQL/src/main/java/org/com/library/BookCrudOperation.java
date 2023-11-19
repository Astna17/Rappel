package org.com.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperation implements CrudOperation <Book>{
    private List<Book> bookList;

    public BookCrudOperation() {
        this.bookList = new ArrayList<>();
    }
    private Connection connection;

    public BookCrudOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try {
            String query = "SELECT * FROM Book";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setIdBook(resultSet.getInt("idBook"));
                    book.setBookName(resultSet.getString("bookName"));
                    book.setIdAuthor(Author.valueOf(resultSet.getInt("idAuthor")));
                    book.setTopic(Topic.valueOf(resultSet.getString("topic")));

                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  books;
    }
    @Override
    public List<Book> saveAll (List<Book> toSave) {
        String insertQuery = "INSERT INTO book (bookName, idAuthor, topic) VALUES (?, ?, ?)";

        try {

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                for (Book book : toSave) {
                    statement.setString(1, book.getBookName());
                    statement.setInt(2, book.getIdAuthor());
                    statement.setString(3, book.getTopic().toString());

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toSave;
    }

    @Override
    public Book save(Book toSave) {
        String insertQuery = "INSERT INTO Book (bookName, idAuthor, topic) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, toSave.getBookName());
            statement.setInt(2, toSave.getIdAuthor());
            statement.setString(3, toSave.getTopic().toString());


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to save book, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Book delete(Book toDelete) {
        String deleteQuery = "DELETE FROM Book WHERE idBook = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, toDelete.getIdBook());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete book, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}
