//adatbázis
CREATE DATABASE "Oneletrajz"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Hungarian_Hungary.1250'
    LC_CTYPE = 'Hungarian_Hungary.1250'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

//táblák:	
CREATE TABLE cv (
    id SERIAL,
    name text,
	birthplace text,
	birthdate date,
	nationality text,
    email text,
	phone text,
	create_date date,
    CONSTRAINT cv_pk PRIMARY KEY (id)
);

INSERT INTO cv(id,name,birthplace,birthdate,nationality,email,phone,create_date) VALUES (1,'Tóth Gergely','Kaposvár','1996-05-20','magyar','tothgergely652@gmail.com','06203316076','2017-01-10');

CREATE TABLE studies (
    id SERIAL,
	cv_id integer NOT NULL REFERENCES cv (id),
    study_name text,
	study_start date,
	study_finish date,
	restult text,
    CONSTRAINT study_pk PRIMARY KEY (id)
);

INSERT INTO studies(id,cv_id,study_name,study_start,study_finish,restult) VALUES (1,1,'Mátyás Király Gimnázium','2011-09-02','2015-06-16','Érettségi');
INSERT INTO studies(id,cv_id,study_name,study_start,study_finish,restult) VALUES (2,1,'PTE TTK Proginfo','2015-09-05',null,null);

CREATE TABLE job (
    id SERIAL,
	cv_id integer NOT NULL REFERENCES cv (id),
    job_name text,
	job_start date,
	job_end date,
	position text,
	role text,
    CONSTRAINT job_pk PRIMARY KEY (id)
);

INSERT INTO job(id,cv_id,job_name,job_start,job_end,position,role) VALUES (1,1,'job','2017-01-13',null,'pos','role');

// *role text: posgresql-ben nincs longtext
 
drop table job;
drop table studies;
drop table cv;