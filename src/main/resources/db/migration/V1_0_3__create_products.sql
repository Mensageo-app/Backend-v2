CREATE TABLE product (
    id integer PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL,
    description varchar(500),
    icon varchar(255)
);