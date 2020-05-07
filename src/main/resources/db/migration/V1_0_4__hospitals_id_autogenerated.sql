ALTER TABLE hospital
ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY;

SELECT SETVAL('hospital_id_seq', (SELECT MAX(id) + 1 FROM hospital));