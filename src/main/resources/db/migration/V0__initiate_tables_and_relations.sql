CREATE TABLE customers (
    id BIGSERIAL,
    username VARCHAR(32) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT customers_pk PRIMARY KEY (id)
);

CREATE TABLE roles (
    customer_id BIGINT NOT NULL,
    role VARCHAR(16) NOT NULL,
    CONSTRAINT authorities_pk PRIMARY KEY (customer_id, role),
    FOREIGN KEY (customer_id) REFERENCES customers (id)
    NOT DEFERRABLE INITIALLY IMMEDIATE
);

CREATE TABLE accounts (
    id BIGSERIAL,
    number VARCHAR(24) UNIQUE NOT NULL,
    balance DECIMAL(15,2) NOT NULL,
    type VARCHAR(16) NOT NULL,
    owner_id BIGINT NOT NULL,
    CONSTRAINT accounts_pk PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES customers (id)
    NOT DEFERRABLE INITIALLY IMMEDIATE
);

CREATE TABLE transactions (
    id BIGSERIAL,
    destination VARCHAR(24),
    source_id BIGINT NOT NULL,
    type VARCHAR(16) NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    timestamp TIMESTAMPTZ NOT NULL,
    CONSTRAINT transactions_pk PRIMARY KEY (id),
    FOREIGN KEY (source_id) REFERENCES accounts (id)
    NOT DEFERRABLE INITIALLY IMMEDIATE
);

