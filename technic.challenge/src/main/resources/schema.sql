CREATE TABLE IF NOT EXISTS usuario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    edad INTEGER,
    rol VARCHAR(255)
);