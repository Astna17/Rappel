CREATE TABLE IF NOT EXISTS "Author"(
   idAuthor int primary key,
   authorName varchar (100) not null,
    gender char not null
    );

INSERT INTO "Author" (idAuthor, authorName, gender) VALUES
    (1, 'F. Scott Fitzgerald', 'M'),
    (2, 'Harper Lee', 'M'),
    (3, 'George Orwell', 'M');