CREATE TABLE user_ratings
(
    user_id BIGINT,
    rating  INTEGER,
    CONSTRAINT pk_user_ratings PRIMARY KEY (user_id, rating),
    CONSTRAINT fk_user_ratings_user FOREIGN KEY (user_id) REFERENCES users (id)
);