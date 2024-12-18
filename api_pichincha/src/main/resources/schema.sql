CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Usamos AUTO_INCREMENT para la columna de ID
    name VARCHAR(255) NOT NULL, -- Nombre del cliente
    email VARCHAR(255) UNIQUE NOT NULL, -- Email único
    gender VARCHAR(50) NOT NULL, -- Género del cliente
    status VARCHAR(50)  -- Estado del cliente
);
