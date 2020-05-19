-- Create one product in db
DELETE FROM email;
DELETE FROM hospital_needs;
DELETE FROM product where id = 1;
INSERT INTO product (id, name, description) values (1, 'Product 1', 'This is product 1');
DELETE FROM hospital where id = 1;
INSERT INTO hospital(id, name, email) values (1, 'The Hospital', 'adm@thehospital.com');
INSERT INTO hospital_needs(id, hospital_id, product_id, quantity) values (1,1,1,100);



