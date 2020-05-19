CREATE TABLE HOSPITAL_NEEDS(
id bigserial PRIMARY KEY,
hospital_id bigint NOT NULL,
product_id bigint NOT NULL,
quantity bigint NOT NULL,
request_timestamp timestamp NOT NULL default now(),
 FOREIGN KEY(hospital_id) REFERENCES hospital(id),
 FOREIGN KEY(product_id) REFERENCES product(id));
