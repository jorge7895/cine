-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: grupo03-ejerc014/changelog-2.0.0.xml
-- Ran at: 8/15/22, 7:44 PM
-- Against: null@offline:h2
-- Liquibase version: 4.15.0
-- *********************************************************************

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-1::curso04 (generated)
CREATE TABLE PUBLIC.detalle_venta (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, active BOOLEAN NOT NULL, importe FLOAT(12) NOT NULL, fk_detalleventa_1 BIGINT, fk_detalleventa_2 BIGINT, CONSTRAINT PK_DETALLE_VENTA PRIMARY KEY (id));

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-2::curso04 (generated)
CREATE TABLE PUBLIC.entrada (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, descuento INT, fk_sesion BIGINT, CONSTRAINT PK_ENTRADA PRIMARY KEY (id));

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-3::curso04 (generated)
CREATE TABLE PUBLIC.sala (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, capacidad INT NOT NULL, CONSTRAINT PK_SALA PRIMARY KEY (id));

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-4::curso04 (generated)
CREATE TABLE PUBLIC.sesion (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, aforo INT NOT NULL, duracion_min INT NOT NULL, hora_empieza TIMESTAMP, pelicula VARCHAR(255), fk_sala BIGINT, CONSTRAINT PK_SESION PRIMARY KEY (id));

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-5::curso04 (generated)
CREATE TABLE PUBLIC.venta (id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL, fh_creacion TIMESTAMP, fh_modificado TIMESTAMP, importe_total FLOAT(12) NOT NULL, CONSTRAINT PK_VENTA PRIMARY KEY (id));

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-6::curso04 (generated)
CREATE INDEX PUBLIC.fk_entrada_detalleVentaId ON PUBLIC.detalle_venta(fk_detalleventa_1);

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-7::curso04 (generated)
CREATE INDEX PUBLIC.fk_entrada_sesionId ON PUBLIC.entrada(fk_sesion);

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-8::curso04 (generated)
CREATE INDEX PUBLIC.fk_sala_sesionId ON PUBLIC.sesion(fk_sala);

-- Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-9::curso04 (generated)
CREATE INDEX PUBLIC.fk_venta_detalleVentaId ON PUBLIC.detalle_venta(fk_detalleventa_2);
