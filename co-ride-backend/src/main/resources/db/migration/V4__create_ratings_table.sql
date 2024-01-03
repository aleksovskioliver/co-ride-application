create table ratings
(
    id        bigserial primary key,
    driver_id bigint references users (id),
    score     bigint,
    rider_id  bigint references users (id)
)