CREATE TABLE HOSPITAL_NEEDS(
id integer PRIMARY KEY,
hospital_id integer NOT NULL,
product_id integer NOT NULL,
quantity integer NOT NULL,
request_timestamp timestamp NOT NULL,
 FOREIGN KEY(hospital_id) REFERENCES hospital(id));
