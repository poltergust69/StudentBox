CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles (
    id uuid primary key,
    name varchar(64) not null unique
);

insert into roles (id, name) values
    (uuid_generate_v4(), 'ADMIN'),
    (uuid_generate_v4(), 'STUDENT'),
    (uuid_generate_v4(), 'COMPANY');

CREATE TABLE users (
    id uuid primary key,
    email varchar(255) not null unique,
    password varchar(255) not null,
    role_id uuid not null,
    avatar_url varchar(512),
    constraint fk_role foreign key (role_id) references roles(id)
);

CREATE TABLE students (
    id uuid primary key,
    first_name varchar (64) not null,
    last_name varchar (96) not null,
    date_of_birth date,
    description varchar (1000),
    constraint fk_student_user foreign key (id) references users(id)
);

CREATE TABLE companies (
    id uuid primary key,
    company_name varchar(256) not null,
    constraint fk_company_user foreign key (id) references users(id)
);

CREATE TABLE posts (
    id uuid primary key,
    title varchar(100) not null,
    content text not null,
    author_id uuid not null,
    constraint fk_post_author foreign key (author_id) references users(id)
);

CREATE TABLE posts_likes (
    id uuid primary key,
    post_id uuid not null,
    user_id uuid not null,
    constraint fk_postlike_user foreign key (user_id) references users(id),
    constraint fk_postlike_post foreign key (post_id) references posts(id),
    constraint ch_postlike_unique unique (post_id, user_id)
);

CREATE TABLE post_replies (
    id uuid primary key,
    author_id uuid not null,
    post_id uuid not null,
    content text not null,
    constraint ch_reply_author foreign key (author_id) references users(id)
);

CREATE TABLE skills (
    id uuid primary key,
    name varchar(100) not null unique
);

CREATE TABLE student_skills (
    id uuid primary key,
    skill_id uuid not null,
    student_id uuid not null,
    constraint fk_skillstudent_student foreign key (student_id) references students(id),
    constraint fk_skillstudent_skill foreign key (skill_id) references skills(id),
    constraint ch_skillstudent_unique unique (skill_id, student_id)
);

CREATE TABLE student_certificates (
    id uuid primary key,
    student_id uuid not null,
    name varchar(100) not null,
    issued_by varchar(100) not null,
    description varchar(250),
    document_url varchar(512),
    issued_at date not null,
    constraint fk_certificates_student foreign key (student_id) references students(id)
);

CREATE TABLE educations(
    id uuid primary key,
    name varchar(250) not null
);

CREATE TABLE student_educations(
    id uuid primary key,
    student_id uuid not null,
    education_id uuid not null,
    description varchar(250),
    started_at date not null,
    ended_at date,
    constraint fk_studenteducations_student foreign key (student_id) references students(id),
    constraint fk_studenteducations_education foreign key (education_id) references educations(id),
    constraint fk_studenteducations_unique unique (student_id, education_id),
    constraint ch_studenteducations_dates CHECK (ended_at is not null and started_at < ended_at)
);

CREATE TABLE job_positions(
    id uuid primary key,
    name varchar(100) not null unique
);

CREATE OR REPLACE FUNCTION check_if_company_exists(_id uuid)
    RETURNS bool
    language plpgsql
    AS
    $func$
    declare
        company_exists bool;
    BEGIN
            SELECT NOT EXISTS (SELECT 1 FROM companies WHERE id = $1)
            INTO company_exists;
            return company_exists;
    end;
    $func$;

CREATE TABLE employment_infos(
    id uuid primary key,
    student_id uuid not null,
    company_id uuid,
    company_name varchar(100),
    position_id uuid not null,
    started_at date not null,
    ended_at date,
    constraint fk_employmentinfos_position FOREIGN KEY (position_id) references job_positions(id),
    constraint fk_employmentinfos_student FOREIGN KEY (student_id) REFERENCES students(id),
    constraint fk_employmentinfos_company CHECK ( (company_id is null and company_name is not null) or
                                               (company_id is not null and company_name is null and
                                                check_if_company_exists(company_id))
     ),
    constraint ch_employmentinfos_dates CHECK (ended_at is not null and started_at < ended_at)
);

CREATE TABLE job_offers(
    id uuid primary key,
    position_id uuid not null,
    company_id uuid not null,
    description text not null,
    monthly_salary_in_euros int not null,
    constraint fk_joboffers_position FOREIGN KEY (position_id) references job_positions(id),
    constraint fk_joboffers_company FOREIGN KEY (company_id) references companies(id),
    constraint ch_joboffers_salary check (monthly_salary_in_euros > 0)
);

CREATE TABLE student_job_offers(
    id uuid primary key,
    offer_id uuid not null,
    student_id uuid not null,
    status varchar(16) not null,
    constraint fk_studentjoboffers_offer FOREIGN KEY (offer_id) references job_offers(id),
    constraint fk_studentjoboffers_student FOREIGN KEY (student_id) references students(id),
    constraint ch_studentjoboffers_status CHECK (status = 'ACCEPTED' or status = 'DECLINED'),
    constraint ch_studentjoboffers_unique UNIQUE (offer_id, student_id)
);

CREATE TABLE company_job_offers(
    id uuid primary key,
    offer_id uuid not null,
    student_id uuid not null,
    status varchar(16) not null,
    constraint fk_companyjoboffers_offer FOREIGN KEY (offer_id) references job_offers(id),
    constraint fk_companyjoboffers_student FOREIGN KEY (student_id) references students(id),
    constraint ch_companyjoboffers_status CHECK (status = 'ACCEPTED' or status = 'DECLINED'),
    constraint ch_companyjoboffers_unique UNIQUE (offer_id, student_id)
);

CREATE TABLE student_dislikes_company(
    id uuid primary key,
    student_id uuid not null,
    company_id uuid not null,
    constraint fk_studentdislikescompany_company FOREIGN KEY (company_id) references companies(id),
    constraint fk_studentdislikescompany_student FOREIGN KEY (student_id) references students(id),
    constraint ch_studentdislikescompany_unique UNIQUE (student_id, company_id)
);

CREATE TABLE required_skills_for(
   id uuid primary key,
   skill_id uuid not null,
   job_id uuid not null,
   constraint fk_requiredskillsfor_job FOREIGN KEY (skill_id) references skills(id),
   constraint fk_requiredskillsfor_skill FOREIGN KEY (job_id) references job_offers(id),
   constraint ch_requiredskillsfor_unique UNIQUE (skill_id,job_id)
);

CREATE TABLE  match_student_job_offer(
   id uuid primary key,
   student_id uuid not null,
   job_id uuid not null,
   student_status varchar(1),
   company_status varchar(1),
   score double precision,
   constraint  fk_matchstudentjoboffer_student FOREIGN KEY (student_id) references students(id),
   constraint fk_matchstudentjoboffer_job FOREIGN KEY (job_id) references job_offers(id),
   constraint fk_matchstudentjoboffer_unique UNIQUE (job_id,student_id),
   constraint ch_matchstudentjoboffer_studentstatus CHECK (student_status = 'A' or student_status = 'W' or student_status = 'D'),
   constraint ch_matchstudentjoboffer_companystatus CHECK (company_status = 'A' or company_status = 'W' or company_status = 'D')
);
