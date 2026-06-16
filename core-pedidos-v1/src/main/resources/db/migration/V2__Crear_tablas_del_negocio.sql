CREATE TABLE cargas_idempotencia (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    idempotency_key VARCHAR(255) NOT NULL UNIQUE,
    archivo_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT UK_idempotency_hash UNIQUE (idempotency_key, archivo_hash)
);

CREATE TABLE zonas (
    id VARCHAR(50) PRIMARY KEY,
    soporte_refrigeracion BOOLEAN DEFAULT TRUE
);

CREATE TABLE clientes (
    id VARCHAR(50) PRIMARY KEY,
    activo BOOLEAN NOT NULL
);

CREATE TABLE pedidos (
    id UUID PRIMARY KEY,
    numero_pedido VARCHAR(50) NOT NULL UNIQUE,
    cliente_id VARCHAR(50) NOT NULL,
    zona_id VARCHAR(50) NOT NULL,
    fecha_entrega DATE NOT NULL,
    estado VARCHAR(30) NOT NULL CHECK (estado IN ('PENDIENTE', 'CONFIRMADO', 'ENTREGADO')),
    requiere_refrigeracion BOOLEAN NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_pedidos_clientes FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_pedidos_zonas FOREIGN KEY (zona_id) REFERENCES zonas(id),
    CONSTRAINT UK_pedido_numero UNIQUE (numero_pedido)
);

CREATE INDEX IDX_pedido_estado_fecha ON pedidos(numero_pedido);