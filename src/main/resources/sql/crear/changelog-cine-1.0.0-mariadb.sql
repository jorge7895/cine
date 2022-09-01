--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: cine.xml
--  Ran at: 8/22/22, 8:01 PM
--  Against: null@offline:mysql
--  Liquibase version: 4.14.0
--  *********************************************************************

--  Changeset cine.xml::1661191151520-1::curso01 (generated)
CREATE TABLE entrada (id BIGINT AUTO_INCREMENT NOT NULL, activa BIT NOT NULL, butaca INT NOT NULL, fila INT NOT NULL, tipo_entrada INT NOT NULL, proyeccion_id BIGINT NOT NULL, venta_id BIGINT NOT NULL, CONSTRAINT PK_ENTRADA PRIMARY KEY (id));

--  Changeset cine.xml::1661191151520-2::curso01 (generated)
CREATE TABLE proyeccion (id BIGINT AUTO_INCREMENT NOT NULL, duracion_min INT NOT NULL, entradas_vendidas INT NOT NULL, fecha_apertura date NOT NULL, fecha_cierre date NOT NULL, hora_proyeccion datetime NOT NULL, pelicula VARCHAR(255) NOT NULL, sesion INT NOT NULL, sala_id BIGINT NOT NULL, CONSTRAINT PK_PROYECCION PRIMARY KEY (id));

--  Changeset cine.xml::1661191151520-3::curso01 (generated)
CREATE TABLE sala (id BIGINT AUTO_INCREMENT NOT NULL, aforo INT NOT NULL, butacas_fila INT NOT NULL, filas INT NOT NULL, CONSTRAINT PK_SALA PRIMARY KEY (id));

--  Changeset cine.xml::1661191151520-4::curso01 (generated)
CREATE TABLE venta (id BIGINT AUTO_INCREMENT NOT NULL, activa BIT NOT NULL, dia_de_venta datetime NOT NULL, importe_total FLOAT(12) NOT NULL, CONSTRAINT PK_VENTA PRIMARY KEY (id));

--  Changeset cine.xml::1661191151520-5::curso01 (generated)
ALTER TABLE entrada ADD CONSTRAINT UniqueEntrada UNIQUE (proyeccion_id, butaca, activa);

--  Changeset cine.xml::1661191151520-6::curso01 (generated)
ALTER TABLE proyeccion ADD CONSTRAINT UniqueProyeccion UNIQUE (sala_id, sesion, hora_proyeccion);

--  Changeset cine.xml::1661191151520-7::curso01 (generated)
CREATE INDEX FKflvnd5s54v8g4gk4me7c7qm2l ON entrada(venta_id);

