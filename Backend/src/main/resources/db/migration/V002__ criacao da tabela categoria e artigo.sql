create table category(
id smallserial not null primary key,
name varchar(20) not null
);

create table article(
id serial not null primary key,
tittle varchar(50) not null,
summary varchar(200) not null,
category_id integer not null references category(id) on delete cascade,
created_by integer not null references  journalist(id)  on delete cascade,
state varchar check(
state='APPLIED' or state='APPROVED' or state='REFUSED'
) not null default 'APPLIED',
path varchar(100) not null,
keywords varchar(200) null,
position smallint null
);