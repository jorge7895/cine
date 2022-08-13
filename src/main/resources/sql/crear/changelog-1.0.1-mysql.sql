--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: grupo03-ejerc014/changelog-1.0.1.xml
--  Ran at: 8/13/22, 10:05 AM
--  Against: null@offline:mysql
--  Liquibase version: 4.15.0
--  *********************************************************************

--  Changeset grupo03-ejerc014/changelog-1.0.1.xml::1660377911473-1::curso04 (generated)
CREATE TABLE hibernate_sequence (next_val BIGINT NULL);

--  Changeset grupo03-ejerc014/changelog-1.0.1.xml::1660377911473-2::curso04 (generated)
CREATE TABLE sesion (id BIGINT NOT NULL, butacas INT NOT NULL, sala_id BIGINT NOT NULL, CONSTRAINT PK_SESION PRIMARY KEY (id));

--  Changeset grupo03-ejerc014/changelog-1.0.1.xml::1660377911473-3::curso04 (generated)
CREATE TABLE venta (id BIGINT NOT NULL, numero_entradas INT NOT NULL, precio DOUBLE NOT NULL, sala_id INT NOT NULL, sesion_id INT NOT NULL, CONSTRAINT PK_VENTA PRIMARY KEY (id));

