CREATE TABLE warehouse.t_product
(
    id       UUID PRIMARY KEY,
    order_id UUID         NOT NULL,
    name     VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL
);
