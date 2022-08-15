--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: grupo03-ejerc014/changelog-2.0.0.xml
--  Ran at: 8/15/22, 7:45 PM
--  Against: null@offline:mysql
--  Liquibase version: 4.15.0
--  *********************************************************************

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-1::curso04 (generated)
CREATE TABLE detalle_venta (id BIGINT AUTO_INCREMENT NOT NULL, active BIT(1) NOT NULL, importe FLOAT(12) NOT NULL, fk_detalleventa_1 BIGINT NULL, fk_detalleventa_2 BIGINT NULL, CONSTRAINT PK_DETALLE_VENTA PRIMARY KEY (id));

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-2::curso04 (generated)
CREATE TABLE entrada (id BIGINT AUTO_INCREMENT NOT NULL, descuento INT NULL, fk_sesion BIGINT NULL, CONSTRAINT PK_ENTRADA PRIMARY KEY (id));

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-3::curso04 (generated)
CREATE TABLE sala (id BIGINT AUTO_INCREMENT NOT NULL, capacidad INT NOT NULL, CONSTRAINT PK_SALA PRIMARY KEY (id));

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-4::curso04 (generated)
CREATE TABLE sesion (id BIGINT AUTO_INCREMENT NOT NULL, aforo INT NOT NULL, duracion_min INT NOT NULL, hora_empieza datetime NULL, pelicula VARCHAR(255) NULL, fk_sala BIGINT NULL, CONSTRAINT PK_SESION PRIMARY KEY (id));

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-5::curso04 (generated)
CREATE TABLE venta (id BIGINT AUTO_INCREMENT NOT NULL, fh_creacion datetime NULL, fh_modificado datetime NULL, importe_total FLOAT(12) NOT NULL, CONSTRAINT PK_VENTA PRIMARY KEY (id));

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-6::curso04 (generated)
CREATE INDEX fk_entrada_detalleVentaId ON detalle_venta(fk_detalleventa_1);

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-7::curso04 (generated)
CREATE INDEX fk_entrada_sesionId ON entrada(fk_sesion);

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-8::curso04 (generated)
CREATE INDEX fk_sala_sesionId ON sesion(fk_sala);

--  Changeset grupo03-ejerc014/changelog-2.0.0.xml::1660585487956-9::curso04 (generated)
CREATE INDEX fk_venta_detalleVentaId ON detalle_venta(fk_detalleventa_2);

