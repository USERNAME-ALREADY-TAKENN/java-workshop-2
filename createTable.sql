create table users
(
    id       int auto_increment
        primary key,
    name     varchar(255) not null,
    password varchar(255) not null,
    email    varchar(255) not null,
    constraint email
        unique (email)
);

