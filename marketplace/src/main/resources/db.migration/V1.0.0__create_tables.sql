CREATE TABLE localizao (
    id int8 NOT NULL,
    latitude float8 NULL,
    longitude float8 NULL,
    CONSTRAINT localizacao_pk PRIMARY KEY (id)
);

CREATE TABLE restaurante (
    id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    nome varchar(50) NOT NULL,
    localizao_id int8 NOT NULL,
    CONSTRAINT restaurante_pkey PRIMARY KEY (id)
);

ALTER TABLE restaurante ADD CONSTRAINT res_loc FOREIGN KEY (localizao_id) REFERENCES localizao(id);

CREATE TABLE prato (
    id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    descricao varchar NULL,
    nome varchar(50) NOT NULL,
    preco numeric(10,2) NOT NULL,
    restaurante_id int8 NOT NULL
);

ALTER TABLE prato ADD CONSTRAINT prato_rest FOREIGN KEY (restaurante_id) REFERENCES restaurante(id);

CREATE TABLE pratro_cliente (
    prato int,
    cliente varchar(200)
);

INSERT INTO localizacao VALUES(1000, -15.817759, -47.836959);

INSERT INTO restaurante VALUES (999, 1000, 'Manguai');

INSERT INTO prato VALUES(9998, 'Cuscuz com Ovo', 'Bom demais no café da manhã', 999, 3.99);

INSERT INTO prato VALUES(9997, 'Peixe frito', 'Agulhinha frita, excelente com Cerveja', 999, 99.99);