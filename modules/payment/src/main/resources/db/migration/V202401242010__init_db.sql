CREATE TABLE payment.t_payment
(
    id       UUID PRIMARY KEY,
    order_id UUID         NOT NULL,
    user_id   UUID         NOT NULL,
    amount   BIGINT       NOT NULL,
    status   VARCHAR(255) NOT NULL
);
