create table valute
(
    id        varchar(255) not null constraint valute_pkey primary key,
    char_code varchar(255),
    name      varchar(255),
    nominal   integer,
    num_code  integer,
    value     varchar(255),
    date      varchar(255) constraint fk4re6pryj8fnlpex8t31ilis5t references val_curs
);