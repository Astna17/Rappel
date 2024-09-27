CREATE TABLE IF NOT EXISTS "Book"(
    idBook int primary key,
    bookName varchar (100) not null ,
    idAuthor int not null,
    topic Topic not null
    );

INSERT INTO "Book" (idBook, bookName, idAuthor, topic) VALUES
    (1, 'The Great Gatsby', 2, 'ROMANCE'),
    (2, 'To Kill a Mockingbird', 1, 'OTHER'),
    (3, '1984', 3, 'OTHER');