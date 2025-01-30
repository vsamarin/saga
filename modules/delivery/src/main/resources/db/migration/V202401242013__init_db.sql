CREATE TABLE delivery.t_courier
(
    id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    order_id UUID         NOT NULL,
    name     VARCHAR(255) NOT NULL
);
