 create table journalist(
 id serial not null primary key,
 is_publisher boolean not null default false, 	 
 first_name varchar(10) not null,
	 last_name VARCHAR(20) NOT NULL,
email VARCHAR(100) NOT NULL,
recruitment_date DATE NOT NULL,

experience_level varchar(10) check(
	experience_level='JUNIOR' or experience_level='MID_LEVEL'
	or experience_level='SENIOR') NOT NULL DEFAULT 'JUNIOR',
username VARCHAR(30) NOT NULL,

password VARCHAR(225) NOT NULL ,
resume VARCHAR(300) NULL
 );
 