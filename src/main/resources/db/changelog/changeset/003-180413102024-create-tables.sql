CREATE TABLE t1_demo_correction.transaction_errors
(
    id             BIGINT NOT NULL PRIMARY KEY,
    transaction_id BIGINT not null unique,
    account_id     BIGINT,
    amount         DECIMAL(19, 2),
    description    VARCHAR(255),
    type           VARCHAR(25)
);