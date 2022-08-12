--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: mifichero.xml
--  Ran at: 8/9/22, 11:57 AM
--  Against: null@offline:mysql
--  Liquibase version: 4.14.0
--  *********************************************************************

--  Changeset mifichero.xml::1659956483312-1::curso04 (generated)
CREATE TABLE cuenta (id BIGINT NOT NULL, activa BIT(1) NOT NULL, num_cuenta VARCHAR(255) NOT NULL, saldo DOUBLE NOT NULL, CONSTRAINT PK_CUENTA PRIMARY KEY (id), UNIQUE (num_cuenta));

--  Changeset mifichero.xml::1659956483312-2::curso04 (generated)
CREATE TABLE hibernate_sequence (next_val BIGINT NULL);

--  Changeset mifichero.xml::1659956483312-3::curso04 (generated)
CREATE TABLE registro_operacion (id BIGINT NOT NULL, saldo DOUBLE NOT NULL, tipo VARCHAR(255) NULL, fk_cuenta_destino BIGINT NULL, fk_cuenta_origen BIGINT NULL, CONSTRAINT PK_REGISTRO_OPERACION PRIMARY KEY (id));

--  Changeset mifichero.xml::1659956483312-4::curso04 (generated)
CREATE INDEX fk_cuenta_cuentaDestino ON registro_operacion(fk_cuenta_destino);

--  Changeset mifichero.xml::1659956483312-5::curso04 (generated)
CREATE INDEX fk_cuenta_cuentaOrigen ON registro_operacion(fk_cuenta_origen);

--  Changeset mifichero.xml::1659956483312-6::curso04 (generated)
ALTER TABLE registro_operacion ADD CONSTRAINT fk_cuenta_cuentaDestino FOREIGN KEY (fk_cuenta_destino) REFERENCES cuenta (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

--  Changeset mifichero.xml::1659956483312-7::curso04 (generated)
ALTER TABLE registro_operacion ADD CONSTRAINT fk_cuenta_cuentaOrigen FOREIGN KEY (fk_cuenta_origen) REFERENCES cuenta (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

