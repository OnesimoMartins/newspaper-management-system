create table approvation(
id serial primary key,
mark smallint not null check(mark>=0 and mark<=10),
comment varchar(200) null,
journalist_id integer not null references journalist(id) on delete cascade,
article_id integer not null references article(id) on delete cascade,
date timestamp default NOW()
)