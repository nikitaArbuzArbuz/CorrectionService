CREATE TABLE t1_demo_correction.transaction_errors
(
    id             BIGINT NOT NULL PRIMARY KEY,
    transaction_id BIGINT,
    account_id     BIGINT,
    amount         DECIMAL(19, 2),
    description    VARCHAR(255),
    type           VARCHAR(25)
);