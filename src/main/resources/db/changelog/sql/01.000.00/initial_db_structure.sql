--liquibase formatted sql

--changeset butylchenko-sa:create_tables_for_OFFER

create table EXCHANGE_RATE
(
    NUM_CODE  varchar(50) NOT NULL,
    CHAR_CODE varchar(50) NOT NULL,
    NOMINAL   varchar(50),
    NAME      varchar(50) NOT NULL,
    VALUE     varchar(50) NOT NULL,
    DATE      DATE        NOT NULL
);

comment ON TABLE EXCHANGE_RATE is 'Таблица для хранения котировок';

comment ON COLUMN EXCHANGE_RATE.NUM_CODE is 'Числовой код валюты';
comment ON COLUMN EXCHANGE_RATE.CHAR_CODE is 'Символьный код валюты';
comment ON COLUMN EXCHANGE_RATE.NOMINAL is 'Номинал валюты';
comment ON COLUMN EXCHANGE_RATE.NAME is 'Наименование валюты';
comment ON COLUMN EXCHANGE_RATE.VALUE is 'Номинал валюты';
comment ON COLUMN EXCHANGE_RATE.DATE is 'Дата';

