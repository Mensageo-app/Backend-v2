CREATE TABLE product (
    id bigserial PRIMARY KEY,
    name varchar(255) UNIQUE NOT NULL,
    description varchar(500),
    icon varchar(255)
);