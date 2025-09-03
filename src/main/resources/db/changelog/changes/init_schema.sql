CREATE TABLE IF NOT EXISTS shapes (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL
);

CREATE TABLE IF NOT EXISTS shape_parameters (
    shape_id BIGINT NOT NULL,
    parameter DOUBLE PRECISION NOT NULL,
    CONSTRAINT fk_shape FOREIGN KEY(shape_id) REFERENCES shapes(id) ON DELETE CASCADE,
    CONSTRAINT uq_shape_parameters UNIQUE(shape_id, parameter)
);

CREATE INDEX IF NOT EXISTS idx_shape_parameters_shape_id ON shape_parameters(shape_id);