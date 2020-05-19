DELETE FROM hospital_needs;
DELETE FROM product where id in (1 , 2);
INSERT INTO product (id, name, description) values (1, 'Product 1', 'This is product 1');
INSERT INTO product (id, name, description) values (2, 'Product 3', 'This is product 3');
DELETE FROM hospital where id = 1;
INSERT INTO hospital(id, name, email) values (1, 'The Hospital', 'adm@thehospital.com');