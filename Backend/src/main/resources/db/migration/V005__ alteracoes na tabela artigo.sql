alter table article add column magazine_id integer  references magazine(id);
alter table article add column magazine_edition integer  null;
alter table magazine add column last_edition integer not null default 0;
drop table newspaper_magazine;