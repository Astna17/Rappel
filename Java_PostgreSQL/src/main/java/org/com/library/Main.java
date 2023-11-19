package org.com.library;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static java.sql.DriverManager.getConnection;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {


        try (Connection connection = getConnection(
                "jdbc:postgresql://localhost:5432/library_management",
                "prog_admin",
                "123456")) {



        } catch (SQLException e) {
            e.printStackTrace();
        }

        configureLogger();

        testAuthorCrudOperation();

        testBookCrudOperation();

        testSubscriberCrudOperation();
    }

    private static <Handler> void configureLogger() {
        Logger rootLogger = Logger.getLogger("");
        Handler consoleHandler = (Handler) new ConsoleHandler();
        consoleHandler.toString();
        rootLogger.addHandler((java.util.logging.Handler) consoleHandler);
        rootLogger.toString();
    }

    private static void testAuthorCrudOperation() {
        AuthorCrudOperation authorCrudOperation = new AuthorCrudOperation();


        List<Author> authors = authorCrudOperation.findAll();
        LOGGER.info("Authors found: " + authors);


        Author newAuthor = new Author(0, "New Author", "F");
        Author savedAuthor = authorCrudOperation.save(newAuthor);
        LOGGER.info("Author saved: " + savedAuthor);


        if (!authors.isEmpty()) {
            Author authorToDelete = authors.get(0);
            Author deletedAuthor = authorCrudOperation.delete(authorToDelete);
            LOGGER.info("Author deleted: " + deletedAuthor);
        } else {
            LOGGER.warning("No authors to delete.");
        }
    }


    private static void testBookCrudOperation() {
        LOGGER.info("=== Testing Book CRUD Operations ===");

        BookCrudOperation bookCrudOperation = new BookCrudOperation();
        AuthorCrudOperation authorCrudOperation = new AuthorCrudOperation();

        List<Author> authors = authorCrudOperation.findAll();
        Author author = authors.isEmpty() ? new Author(0, "Default Author", "M") : authors.get(0);

        List<Book> books = bookCrudOperation.findAll();
        LOGGER.info("Books found: " + books);

        Book newBook = new Book(0, "New Book", author,  "OTHER");
        Book savedBook = bookCrudOperation.save(newBook);
        LOGGER.info("Book saved: " + savedBook);

        Book bookToDelete = books.isEmpty() ? savedBook : books.get(0);
        Book deletedBook = bookCrudOperation.delete(bookToDelete);
        LOGGER.info("Book deleted: " + deletedBook);
    }

    private static void testSubscriberCrudOperation() {
        SubscriberCrudOperation subscriberCrudOperation = new SubscriberCrudOperation();

        List<Subscriber> subscribers = subscriberCrudOperation.findAll();
        LOGGER.info("subscribers found: " + subscribers);

        Subscriber subscriber = new Subscriber(0, "New subscriber","new password", "Reference123");
        Subscriber savedVisitor = subscriberCrudOperation.save(subscriber);
        LOGGER.info("Subscribers saved: " + savedVisitor);

        Subscriber subscriberToDelelte = subscribers.get(0);
        Subscriber deletedSubscriber = subscriberCrudOperation.delete(subscriberToDelelte);
        LOGGER.info("Visitor deleted: " + deletedSubscriber);
    }

}
