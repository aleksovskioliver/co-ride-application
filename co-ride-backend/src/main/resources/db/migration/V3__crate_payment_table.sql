create table payments
(
    id                bigserial primary key,
    user_id           bigint references users (id),
    transaction_token text,
    amount            bigint,
    currency          text,
    status            text
);