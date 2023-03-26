CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE user_forgot_password_requests(
    user_id uuid primary key,
    created_at timestamp not null default now(),
    secret_code varchar(100),
    constraint fk_forgotpassword_user foreign key (user_id) references users(id)
);