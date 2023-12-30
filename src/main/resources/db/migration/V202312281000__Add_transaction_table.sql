CREATE TABLE event(
    id UUID constraint pk_event PRIMARY KEY,
    reference_id UUID null,
    raw jsonb not null,
    status varchar(50) not null,
    inserted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);
CREATE INDEX idx_event_insert ON event(inserted_at);
CREATE INDEX idx_event_reference_id ON event(reference_id);


CREATE TABLE delivery (
    id UUID constraint pk_delivery PRIMARY KEY,
    courier_id UUID NOT NULL
);
CREATE INDEX idx_delivery_courier ON delivery(courier_id);


CREATE TABLE transaction (
    id UUID constraint pk_transaction PRIMARY KEY,
    delivery_id UUID NOT NULL,
    type varchar(50) NOT NULL,
    transaction_reference_id UUID NOT NULL,
    occurred_at TIMESTAMP NOT NULL,
    value bigint NOT NULL,
    metadata jsonb,
    inserted_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transaction_delivery
        FOREIGN KEY(delivery_id)
        REFERENCES delivery(id)
);


CREATE INDEX idx_transaction_delivery ON transaction(delivery_id);
CREATE INDEX idx_transaction_timestamp ON transaction(occurred_at);