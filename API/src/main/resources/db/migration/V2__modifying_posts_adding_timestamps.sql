ALTER TABLE posts
    ADD created_at timestamp not null default now(),
    ADD modified_at timestamp not null default now();

ALTER TABLE post_replies
    ADD created_at timestamp not null default now(),
    ADD modified_at timestamp not null default now();

ALTER TABLE job_offers
    ADD created_at timestamp not null default now(),
    ADD modified_at timestamp not null default now();

ALTER TABLE posts_likes rename to post_likes;

ALTER TABLE users
    ADD username varchar(150) not null unique;

CREATE TABLE post_reply_likes(
    id uuid primary key,
    user_id uuid not null,
    reply_id uuid not null,
    CONSTRAINT fk_postreplylikes_user FOREIGN KEY (user_id) references users(id),
    CONSTRAINT fk_postreplylikes_reply FOREIGN KEY (reply_id) references post_replies(id),
    CONSTRAINT ck_postreplylikes_unique unique (user_id, reply_id)
);