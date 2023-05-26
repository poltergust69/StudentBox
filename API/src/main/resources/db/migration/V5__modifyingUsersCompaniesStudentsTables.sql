ALTER TABLE students
    ADD COLUMN user_id uuid,
    DROP CONSTRAINT fk_student_user,
    ADD CONSTRAINT fk_student_user foreign key (user_id) references users(id);

ALTER TABLE companies
    ADD COLUMN user_id uuid,
    DROP CONSTRAINT fk_company_user,
    ADD CONSTRAINT fk_company_user foreign key (user_id) references users(id);