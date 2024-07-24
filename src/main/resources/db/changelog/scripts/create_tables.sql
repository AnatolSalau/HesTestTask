create sequence hes.accounts_id_seq
    start 1
    increment 1;

create sequence hes.transactions_id_seq
    start 1
    increment 1;

create sequence hes.users_id_seq
    start 1
    increment 1;

create type hes.currencytype as enum ('BYN', 'EUR', 'USD');

create type hes.transactiontype as enum ('DEPOSIT', 'WITHDRAWAL');

create type hes.roletype as enum ('ROLE_ADMIN', 'ROLE_OWNER');


create table hes.users
(
    user_id    bigint       not null primary key,
    name       varchar(255) not null,
    password   varchar(255) not null,
    role_type  roletype     not null,
    created_at timestamp(6) not null,
    updated_at timestamp(6) not null
);

create table hes.accounts
(
    account_id    bigint         not null primary key,
    user_id       bigint references hes.users,
    balance       numeric(10, 2) not null,
    is_blocked    boolean        not null,
    currency_type currencytype   not null,
    created_at    timestamp(6)   not null,
    updated_at    timestamp(6)   not null
);

create table hes.transactions
(
    transaction_id   bigint          not null primary key,
    account_id       bigint references hes.accounts,
    amount           numeric(10, 2)  not null,
    created_at       timestamp(6)    not null,
    currency_type    currencytype    not null,
    transaction_type transactiontype not null
);