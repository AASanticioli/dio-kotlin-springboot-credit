CREATE TABLE credits
(
    id                       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    code                     UUID                                    NOT NULL,
    status                   INT,
    credit_value             DECIMAL                                 NOT NULL,
    day_first_of_installment date                                    NOT NULL,
    number_of_installments   INT                                     NOT NULL,
    customer_id              BIGINT                                  NOT NULL,
    CONSTRAINT pk_credits PRIMARY KEY (id)
);

ALTER TABLE credits
    ADD CONSTRAINT uc_credits_code UNIQUE (code);

ALTER TABLE credits
    ADD CONSTRAINT FK_CREDITS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);