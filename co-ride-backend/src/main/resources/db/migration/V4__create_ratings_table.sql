create table ratings
(
    id        bigserial primary key,
    driver_id bigint references users (id),
    score     double precision,
    rider_id  bigint references users (id)
)