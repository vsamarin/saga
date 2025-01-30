CREATE TABLE orders.t_order
(
    id      UUID PRIMARY KEY,
    user_id UUID         NOT NULL,
    price   BIGINT       NOT NULL,
    product UUID         NOT NULL,
    address VARCHAR(255) NOT NULL,
    status  VARCHAR(255) NOT NULL
);
