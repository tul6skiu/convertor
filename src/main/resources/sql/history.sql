create table history
(
    id           bigint not null constraint history_pkey primary key,
    date         date,
    source_cur   varchar(255),
    source_value integer,
    target_cur   varchar(255),
    target_value numeric(19, 2)
);