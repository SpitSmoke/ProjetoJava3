To make this project work, you need to create a Postgres database with the following setup:

```
Postgres <domain>:<port>: localhost:5432
Database name: ebac_store
Username: ebac_user 
Password: secret
```
Then, you need to create some tables following the SQL sample below.

```SQL
CREATE TABLE tb_customer (
    id BIGINT,
    name VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL
);

CREATE SEQUENCE sq_customer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE tb_product (
    id BIGINT NOT NULL,
    code VARCHAR(30),
    description VARCHAR(60),
    price NUMERIC(4,2)
);

CREATE SEQUENCE sq_product
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE tb_customer ADD CONSTRAINT tb_customer_cpf_key UNIQUE (cpf);

ALTER TABLE tb_customer ADD CONSTRAINT tb_customer_id_key UNIQUE (id);

ALTER TABLE tb_product ADD CONSTRAINT tb_product_pkey PRIMARY KEY (id);
```

Now, you need to download a gradle jar by using the following command on the project root folder:

```bash
gradle wrapper 
```

I made some basic tests that can be run with the command below:

```bash
 ./gradlew clean test
```
