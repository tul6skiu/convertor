create table "user"
(
    id              varchar(255) not null constraint user_pkey primary key,
    activation_code varchar(255),
    name            varchar(255),
    password        varchar(255),
    history_id      bigint constraint fkaket2bcsuw7nhwjjdo5xu0axy references history
);
