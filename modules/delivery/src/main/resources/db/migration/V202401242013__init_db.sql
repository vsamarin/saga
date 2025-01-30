CREATE TABLE delivery.t_courier
(
    id       UUID PRIMARY KEY,
    order_id UUID,
    name     VARCHAR(255) NOT NULL
);

INSERT INTO delivery.t_courier(id, name)
VALUES ('5197a6ca-12c7-4ff0-839f-5e2e2f7790c6', 'Самарин Влад');
