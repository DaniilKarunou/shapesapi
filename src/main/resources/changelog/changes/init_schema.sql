CREATE TABLE shapes (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE shapes_parameters (
    shape_entity_id BIGINT NOT NULL,
    parameters DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_shape FOREIGN KEY(shape_entity_id) REFERENCES shapes(id) ON DELETE CASCADE,
    CONSTRAINT uq_shape_parameters UNIQUE(shape_entity_id, parameters)
);

CREATE INDEX idx_shapes_parameters_shape_id ON shapes_parameters(shape_entity_id);