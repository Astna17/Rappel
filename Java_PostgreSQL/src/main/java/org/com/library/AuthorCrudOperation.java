package org.com.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperation implements CrudOperation <Author> {
    private List<Author> authorList;

    public AuthorCrudOperation() {
        this.authorList = new ArrayList<>();
    }
    private Connection connection;

    public AuthorCrudOperation(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try {
            String query = "SELECT * FROM Author";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Author author = new Author();
                    author.setIdAuthor(resultSet.getInt("idAuthor"));
                    author.setAuthorName(resultSet.getString("authorName"));
                    author.setGender(resultSet.getString("gender"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Author> saveAll (List<Author> toSave) {
        String insertQuery = "INSERT INTO Author (idAuthor, authorName, gender) VALUES (?, ?, ?)";

        try {

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                for (Author author : toSave) {
                    statement.setInt(1, author.getIdAuthor());
                    statement.setString(2, author.getAuthorName());
                    statement.setString(3, author.getGender());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toSave;
    }

    @Override
    public Author save(Author toSave) {
        String insertQuery = "INSERT INTO Author (idAuthor, authorName, gender) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, toSave.getIdAuthor());
            statement.setString(2, toSave.getAuthorName());
            statement.setString(3, toSave.getGender());


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to save author, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Author delete(Author toDelete) {
        String deleteQuery = "DELETE FROM Author WHERE idAuthor = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, toDelete.getIdAuthor());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Failed to delete author, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}
