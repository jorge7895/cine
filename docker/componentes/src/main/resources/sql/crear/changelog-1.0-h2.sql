-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: mifichero.xml
-- Ran at: 8/8/22, 1:04 PM
-- Against: null@offline:h2
-- Liquibase version: 4.14.0
-- *********************************************************************

CREATE SEQUENCE hibernate_sequence start with 1 increment by 1;

-- Changeset mifichero.xml::1659956483312-1::curso04 (generated)
CREATE TABLE PUBLIC.cuenta (id BIGINT NOT NULL, activa BOOLEAN NOT NULL, num_cuenta VARCHAR(255) NOT NULL, saldo DOUBLE NOT NULL, CONSTRAINT PK_CUENTA PRIMARY KEY (id), UNIQUE (num_cuenta));

-- Changeset mifichero.xml::1659956483312-2::curso04 (generated)
CREATE TABLE PUBLIC.hibernate_sequence (next_val BIGINT);

-- Changeset mifichero.xml::1659956483312-3::curso04 (generated)
CREATE TABLE PUBLIC.registro_operacion (id BIGINT NOT NULL, saldo DOUBLE NOT NULL, tipo VARCHAR(255), fk_cuenta_destino BIGINT, fk_cuenta_origen BIGINT, CONSTRAINT PK_REGISTRO_OPERACION PRIMARY KEY (id));

-- Changeset mifichero.xml::1659956483312-4::curso04 (generated)
CREATE INDEX PUBLIC.fk_cuenta_cuentaDestino ON PUBLIC.registro_operacion(fk_cuenta_destino);

-- Changeset mifichero.xml::1659956483312-5::curso04 (generated)
CREATE INDEX PUBLIC.fk_cuenta_cuentaOrigen ON PUBLIC.registro_operacion(fk_cuenta_origen);

-- Changeset mifichero.xml::1659956483312-6::curso04 (generated)
ALTER TABLE PUBLIC.registro_operacion ADD CONSTRAINT fk_cuenta_cuentaDestino FOREIGN KEY (fk_cuenta_destino) REFERENCES PUBLIC.cuenta (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- Changeset mifichero.xml::1659956483312-7::curso04 (generated)
ALTER TABLE PUBLIC.registro_operacion ADD CONSTRAINT fk_cuenta_cuentaOrigen FOREIGN KEY (fk_cuenta_origen) REFERENCES PUBLIC.cuenta (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

