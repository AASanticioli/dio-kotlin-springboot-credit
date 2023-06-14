CREATE TABLE customers (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   last_name VARCHAR(255) NOT NULL,
   cpf VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   zip_code VARCHAR(255) NOT NULL,
   street VARCHAR(255) NOT NULL,
   CONSTRAINT pk_customers PRIMARY KEY (id)
);

ALTER TABLE customers ADD CONSTRAINT uc_customers_cpf UNIQUE (cpf);

ALTER TABLE customers ADD CONSTRAINT uc_customers_email UNIQUE (email);