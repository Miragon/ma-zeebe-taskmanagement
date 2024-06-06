CREATE TABLE form_data
(
    id      VARCHAR(255)     NOT NULL,
    version DOUBLE PRECISION NOT NULL,
    type    VARCHAR(255),
    form    VARCHAR,
    CONSTRAINT pk_form_data PRIMARY KEY (id, version)
);

CREATE TABLE order_data
(
    id               BIGINT NOT NULL,
    customer_name    VARCHAR(255),
    delivery_address VARCHAR(255),
    items            VARCHAR(255),
    state            VARCHAR(255),
    CONSTRAINT pk_order_data PRIMARY KEY (id)
);