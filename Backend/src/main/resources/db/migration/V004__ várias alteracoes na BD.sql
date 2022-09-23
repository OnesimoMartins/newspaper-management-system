alter table Approvation  rename to avaliation;
alter table journalist drop column username;
alter table journalist add unique(email);
alter table article alter column summary drop not null;

create table newspaper(
id serial primary key,
name varchar(100) not null
);

create table Administrator(
id serial not null primary key,
first_name varchar(10) not null,
last_name VARCHAR(20) NOT NULL,
email VARCHAR(100) NOT NULL unique,
recruitment_date DATE NOT NULL,
newspaper_id integer NOT NULL references newspaper(id) on delete cascade,

experience_level varchar(10) check(
	experience_level='JUNIOR' or experience_level='MID_LEVEL'
	or experience_level='SENIOR') NOT NULL,
	
password VARCHAR(15) NOT NULL ,
resume VARCHAR(300) NULL,

administrative_role VARCHAR(225) check(
	administrative_role='PUBLISHER' or administrative_role='CEO'
	or administrative_role='HUMAN_RESOURCES')
);
  

alter table newspaper add column ceo integer null references administrator(id) on delete cascade;

create table magazine(
id serial primary key,
name varchar(30) not null,
newspaper_id integer not null references newspaper(id) on delete cascade,
publisher_id integer not null references administrator(id) on delete cascade,
publication_date timestamp default now()
);
create table magazine_article(
magazine_id integer not null references magazine(id) on delete cascade,
article_id integer not null references article(id) on delete cascade,
edition integer not null
);

create table magazine_publication(
magazine_id integer not null references magazine(id),
edition integer not null,
date date not null default NOW()
);

create table newspaper_magazine(
magazine_id integer not null references magazine(id),
newspaper_id integer not null references newspaper(id)
);

alter table journalist add column newspaper_id integer references newspaper(id);