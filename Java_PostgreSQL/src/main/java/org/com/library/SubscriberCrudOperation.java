package org.com.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscriberCrudOperation implements CrudOperation <Subscriber>{
    private Connection connection;


    public SubscriberCrudOperation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Subscriber> findAll() {
        List<Subscriber> subscribers = new ArrayList<>();

        try {
            String query = "SELECT * FROM Subscriber";

            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Subscriber subscriber = new Subscriber();
                    subscriber.setIdSubscriber(resultSet.getInt("idSubscriber"));
                    subscriber.setUserName(resultSet.getString("userName"));
                    subscriber.setPassword(resultSet.getString("password"));
                    subscriber.setReference(resultSet.getString("reference"));

                    subscribers.add(subscriber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscribers;
    }

    @Override
    public List<Subscriber> saveAll(List<Subscriber> toSave) {
        List<Subscriber> subscriber = new ArrayList<>();
        try {
            String query = "INSERT INTO Subscriber (userName) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Subscriber savedSubscriber = new Subscriber();
                    savedSubscriber.setIdSubscriber(resultSet.getInt(1));
                    savedSubscriber.setUserName(toSave.get(1).getUserName());
                    savedSubscriber.setPassword(toSave.get(1).getPassword());
                    savedSubscriber.setReference(toSave.get(1).getReference());
                    subscriber.add(savedSubscriber);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subscriber;
    }

    @Override
    public Subscriber save(Subscriber toSave) {
        Subscriber savedSubscriber = null;
        try {
            String query = "INSERT INTO Subscriber (userName) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, toSave.getUserName());

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 1) {
                    try (ResultSet resultSet = statement.getResultSet()) {
                        if (resultSet.next()) {
                            savedSubscriber = new Subscriber();
                            savedSubscriber.setIdSubscriber(resultSet.getInt(1));
                            savedSubscriber.setUserName(toSave.getUserName());
                        }
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return savedSubscriber;
    }

    @Override
    public Subscriber delete(Subscriber toDelete) {
        try {
            String query = "DELETE FROM Subscriber WHERE idSubscriber = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, toDelete.getIdSubscriber());

                int affectedRows = statement.executeUpdate();

                if (affectedRows == 1) {
                    return toDelete;
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDelete;
    }
}
