CREATE TABLE IF NOT EXISTS "Subscriber"(
    idSubscriber int primary key,
    userName varchar (100) not null,
    password varchar (50) not null,
    reference varchar (10)
    );

INSERT INTO "Subscriber" (idSubscriber, username, password, reference) VALUES
    (1,'Alice', 'password123', 'SUB123'),
    (2,'Bob', 'password456', 'SUB456'),
    (3,'Charlie', 'password789', 'SUB789');