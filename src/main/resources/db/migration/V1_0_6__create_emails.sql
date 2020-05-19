CREATE TABLE email (
id bigserial PRIMARY KEY,
hospital_needs_id bigint NOT NULL,
quantity bigint NOT NULL,
subject varchar(255) NOT NULL,
body varchar(5000) NOT NULL,
name varchar(255) NOT NULL,
company varchar(255) NOT NULL,
phone_number varchar(255) NOT NULL,
description varchar(255) NOT NULL,
created_date_timestamp timestamp NOT NULL default now(),
 FOREIGN KEY(hospital_needs_id) REFERENCES hospital_needs(id));
