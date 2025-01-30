CREATE TABLE warehouse.t_product
(
    id       UUID PRIMARY KEY,
    order_id UUID,
    name     VARCHAR(255) NOT NULL,
    status   VARCHAR(255) NOT NULL
);

INSERT INTO warehouse.t_product(id, name, status)
VALUES ('29282f3f-dec7-45ce-98ba-c654706ab71d', 'Молоко', 'AVAILABLE');
