CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE job_offer_skills(
    id uuid primary key,
    skill_id uuid not null,
    job_id uuid not null,
    constraint fk_jobofferskills_joboffer FOREIGN KEY (skill_id) references skills(id),
    constraint fk_jobofferskills_skill FOREIGN KEY (job_id) references job_offers(id),
    constraint ch_jobofferskills_unique UNIQUE (skill_id,job_id)
);

CREATE TABLE  job_offers_students_companies(
     id uuid primary key,
     student_id uuid not null,
     company_id uuid not null,
     job_id uuid not null,
     student_choice varchar(16),
     company_choice varchar(16),
     score double precision,
     constraint  fk_matchstudentjoboffer_student FOREIGN KEY (student_id) references students(id),
     constraint fk_matchstudentjoboffer_job FOREIGN KEY (job_id) references job_offers(id),
     constraint fk_matchstudentjoboffer_unique UNIQUE (job_id,student_id),
     constraint ch_matchstudentjoboffer_studentstatus CHECK (student_choice = 'ACCEPTED' or student_choice = 'WAITING' or student_choice = 'DECLINED'),
     constraint ch_matchstudentjoboffer_companystatus CHECK (company_choice = 'ACCEPTED' or company_choice = 'WAITING' or company_choice = 'DECLINED')
);