CREATE TABLE email (
id serial PRIMARY KEY,
product_id integer NOT NULL,
quantity integer NOT NULL,
subject varchar(255) NOT NULL,
body varchar(5000) NOT NULL,
name varchar(255) NOT NULL,
company varchar(255) NOT NULL,
phone_number varchar(255) NOT NULL,
description varchar(255) NOT NULL,
created_date_timestamp timestamp NOT NULL default now(),
 FOREIGN KEY(product_id) REFERENCES product(id));
