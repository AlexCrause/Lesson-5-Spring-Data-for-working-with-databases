CREATE TABLE tasks (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    status ENUM('NOT_STARTED', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
    date_create DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (description) -- Добавлено уникальное ограничение на описание
);