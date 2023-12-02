CREATE TABLE IF NOT EXISTS admins (
id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY,
login VARCHAR(255) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
first_name VARCHAR(255) NOT NULL,
last_name VARCHAR(255) NOT NULL,
PRIMARY KEY (id)
);