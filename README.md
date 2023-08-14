# Room Rent 
Soon...

## Instalation
Soon...

## Usage
Soon...

## Database
Run the following code to create the database:

```sql
CREATE DATABASE roomrent;
```

Rune the following code to create the tables:

```sql
CREATE TABLE anuncio (
    id SERIAL,
    titulo VARCHAR(50) NOT NULL,
    local VARCHAR(50) NOT NULL,
    preco INT NOT NULL,
    descricao TEXT NOT NULL,
    genero CHAR(1) NOT NULL,
    anunciante VARCHAR(20) NOT NULL,
    contacto INT NOT NULL,
    tipologia VARCHAR(15) NOT NULL,
    data timestamp(0) without time zone NOT NULL,
    tipo CHAR(1) NOT NULL,
    estado CHAR(1) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE mensagem (
    id SERIAL,
    remetente VARCHAR(20) NOT NULL,
    mensagem TEXT NOT NULL,
    data timestamp(0) without time zone NOT NULL,
    anuncio_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (anuncio_id) REFERENCES anuncio (id)
);

CREATE TABLE users (
    id SERIAL,
    name VARCHAR(20) NOT NULL,
    username VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
```
