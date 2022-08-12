--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: curso04-ejerc013/changelog-2.0.0.xml
--  Ran at: 8/11/22, 9:44 AM
--  Against: null@offline:mysql
--  Liquibase version: 4.15.0
--  *********************************************************************

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-1::curso04 (generated)
CREATE TABLE apunte (id BIGINT AUTO_INCREMENT NOT NULL, externa BIT(1) NOT NULL, importe FLOAT(12) NOT NULL, cuenta_id BIGINT NULL, CONSTRAINT PK_APUNTE PRIMARY KEY (id));

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-2::curso04 (generated)
CREATE TABLE cuenta (id BIGINT AUTO_INCREMENT NOT NULL, activa BIT(1) NOT NULL, iban VARCHAR(255) NOT NULL, saldo DOUBLE NOT NULL, CONSTRAINT PK_CUENTA PRIMARY KEY (id), UNIQUE (iban));

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-3::curso04 (generated)
CREATE TABLE ingreso (id BIGINT AUTO_INCREMENT NOT NULL, apunte_id BIGINT NULL, CONSTRAINT PK_INGRESO PRIMARY KEY (id));

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-4::curso04 (generated)
CREATE TABLE retiro (id BIGINT AUTO_INCREMENT NOT NULL, apunte_id BIGINT NULL, CONSTRAINT PK_RETIRO PRIMARY KEY (id));

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-5::curso04 (generated)
CREATE TABLE transferencia (id BIGINT AUTO_INCREMENT NOT NULL, ingreso_id BIGINT NULL, retiro_id BIGINT NULL, CONSTRAINT PK_TRANSFERENCIA PRIMARY KEY (id));

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-6::curso04 (generated)
CREATE INDEX FK6ve9k0ej3ght64fj4kar62h8h ON ingreso(apunte_id);

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-7::curso04 (generated)
CREATE INDEX FK707y0ebiwruxr5ofedks42aox ON retiro(apunte_id);

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-8::curso04 (generated)
CREATE INDEX FKa728wcqgd0y1si6d84u0hmln9 ON transferencia(ingreso_id);

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-9::curso04 (generated)
CREATE INDEX FKbwkn4y5wfdiqgymq0yjdv0w9c ON transferencia(retiro_id);

--  Changeset curso04-ejerc013/changelog-2.0.0.xml::1660203864273-10::curso04 (generated)
CREATE INDEX FKrs5hx0yw2tc6awund154m5xic ON apunte(cuenta_id);

