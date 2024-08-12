CREATE TABLE orders
(
    id            UUID PRIMARY KEY,
    firstname     VARCHAR(255) NOT NULL,
    lastname      VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    street        VARCHAR(255) NOT NULL,
    city          VARCHAR(255) NOT NULL,
    zip           VARCHAR(20)  NOT NULL,
    delivery_date DATE,
    state         VARCHAR(255) NOT NULL
);

CREATE TABLE items
(
    id    UUID PRIMARY KEY,
    name  VARCHAR(255),
    price DECIMAL(10, 2),
    image VARCHAR(255)
);

CREATE TABLE order_items
(
    id       UUID PRIMARY KEY,
    order_id UUID NOT NULL,
    item_id  UUID NOT NULL,
    quantity INTEGER,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);