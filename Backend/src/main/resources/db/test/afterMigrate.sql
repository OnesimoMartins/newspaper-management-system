
delete from magazine_article;
delete from magazine_publication;
delete from journalist;
delete from administrator;
delete from newspaper;
delete from article;
delete from category;
delete from avaliation;
delete from magazine;
delete from category;

alter sequence magazine_id_seq restart with 1;
alter sequence newspaper_id_seq restart with 1;
alter sequence approvation_id_seq restart with 1;
alter sequence category_id_seq restart with 1;
alter sequence journalist_id_seq restart with 1;
alter sequence administrator_id_seq restart with 1;
alter sequence article_id_seq restart with 1;

insert into newspaper(name) values('Jornal de Angola'),('Jornal Expresso'),
('Angola Today'),('Desporto Máximo'),('Makas da Banda');

insert into administrator(
	first_name,last_name,email,resume,experience_level,
	recruitment_date,administrative_role,newspaper_id,password
)
values
('Joao','Silva','silva@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','CEO',1,'angola123'),
('peterson','Monteiro','peterson@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2007-03-22','HUMAN_RESOURCES',1,'angola123'),
('Peterson','Agostinho','Agostinho@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2007-05-22','HUMAN_RESOURCES',1,'angola123'),
('Axel','xilongo','xilongo@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','PUBLISHER',1,'angola123'),
('José','Palmiere','Palmiere@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','PUBLISHER',1,'angola123'),

('Patrick','Vieira','Vieira@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','CEO',2,'angola123'),
('Emerson','Fonseca','Fonseca@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','HUMAN_RESOURCES',2,'angola123'),
('Augusto','Tomás','Augusto12@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','PUBLISHER',2,'angola123'),
('Rafaela','do Espírito Santo','Santo@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22','PUBLISHER',2,'angola123'),
('Aureo','Belém','Aureo@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','PUBLISHER',2,'angola123'),

('Rafaela','costa','Rafaela@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','CEO',3,'angola123')
,('Catarina',' da Silva','Catarina@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','PUBLISHER',3,'angola123'),
('Pedro','dos Anjos','Anjos@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22','PUBLISHER',3,'angola123'),
('Daniel','Rogério','Daniel@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22','HUMAN_RESOURCES',3,'angola123'),
('António','Portugal','Portugal@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','HUMAN_RESOURCES',3,'angola123'),

('Luiza','Silva','Luiza@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','CEO',4,'angola123')
,('Petra','Monteiro','Petra@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','HUMAN_RESOURCES',4,'angola123'),
('Rafael','Agostinho','Agostinho3@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','PUBLISHER',4,'angola123'),
('André','Amaral','Amaral@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','PUBLISHER',4,'angola123'),
('Max',' António','Max@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','HUMAN_RESOURCES',4,'angola123'),

('Jesus','da Conceição','Jesus@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22','CEO',5,'angola123')
,('Augusta',' dos Santos','Augusta@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22','PUBLISHER',5,'angola123'),
('Silvio','Proença','Silvio@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','HUMAN_RESOURCES',5,'angola123'),
('Josué',' da Costa','Costa@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22','PUBLISHER',5,'angola123'),
('Otávio','Mendes','Mendes1@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22','PUBLISHER',5,'angola123');



insert into journalist(
	first_name,last_name,email,resume,experience_level,
	recruitment_date,newspaper_id,password
)

values('Tomás','Silva','Silva18@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',1,'angola123')
,('peterson','Monteiro','petersonA2@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-05-22',1,'angola'),
('Rafael','Agostinho','Rafael90@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2007-03-24',1,'angola'),
('Alexandre','xilongo','Alexandre@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',1,'angola123'),
('Pedro','Palmiere','Pedro5@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',1,'angola123'),

('Moreno','Vieira','Moreno@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',2,'angola123')
,('Matias','Fonseca','Matias5@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',2,'angola123'),
('Ngola','Tomás','Ngola@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',2,'angola123'),
('Andreia','do Espírito Santo','Andreia@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',2,'angola123'),
('Bruna','Belém','Bruna@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',2,'angola123'),

('Luisa','costa','Luisasilva@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',3,'angola123')
,('Daniela',' da Silva','Daniela55@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',3,'angola123'),
('Eliane','dos Anjos','Anjos5@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',3,'angola123'),
('Rafa','Rogério','rafinha@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22',3,'angola123'),
('Bernardo','Portugal','Bernardo5@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22',3,'angola123'),

('Núria','Silva','lux4@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',4,'angola123')
,('Lena','Monteiro','Monteiro@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',4,'angola123'),
('Valdimiro','Agostinho','Agostinho7@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22',4,'angola123'),
('Augusto','Amaral','Amaral8@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',4,'angola123'),
('Maxewll',' António','Maxewll@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',4,'angola123'),

('António','da Conceição','conce3@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',5,'angola123')
,('Garcia',' dos Santos','Santos67@gmail.com','/resumes/ao-ipqw.pdf','SENIOR','2006-03-22',5,'angola123'),
('Marílio','Proença','Marilio12@gmail.com','/resumes/ao-ipqw.pdf','MID_LEVEL','2006-03-22',5,'angola123'),
('Ganza',' da Costa','Costa6@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',5,'angola123'),
('Daniel','Mendes','Mendes44@gmail.com','/resumes/ao-ipqw.pdf','JUNIOR','2006-03-22',5,'angola123');

update newspaper set ceo=1 where id=1;
update newspaper set ceo=6 where id=2;
update newspaper set ceo=11 where id=3;
update newspaper set ceo=16 where id=4;
update newspaper set ceo=22 where id=5;

insert into category(name)values('Política'),('Desporto'),('Economia'),('Releigião'),('Sociedade')
,('Internacional'),('Outros');

INSERT INTO magazine(newspaper_id,publisher_id,name,last_edition) VALUES
(1,4,'jornal de angola',2),(1,5,'jornal dos Desportos',1)
,(2,8,'Jornal Expresso',1),
(3,12,'Today Journal',1),
(4,18,'Desporto Máximo',1),
(5,24,'Jornal Makas',1);

insert into article(tittle,summary,category_id,created_by,state,path,keywords,position,
magazine_id,magazine_edition) values


('artigo recusado teste','fui recusado',2,3,'REFUSED','src\main\resources\application_files\articles\2008-08-08\9ebfc925-c14f-43e7-8c89-9e1526bd3927.txt',
 '#Fome#ONG#Alimentos',1,null,null),
 

('Artigo Aprovado teste ','Ongs criam projectos para a luta conta a fome',1,2,'APPROVED','src\main\resources\application_files\articles\2008-08-08\9ebfc925-c14f-43e7-8c89-9e1526bd3927.txt',
 '#Fome#ONG#Alimentos',1,null,null),
 
('Luta contra a fome ','Ongs criam projectos para a luta conta a fome',1,2,'APPROVED','src\main\resources\application_files\articles\2008-08-08\9ebfc925-c14f-43e7-8c89-9e1526bd3927.txt',
 '#Fome#ONG#Alimentos',1,1,1),
 ('Liga dos campeões terá novo formato','A EUFA anuciou o novo formato da competicao ',2,3,'APPROVED','src\main\resources\application_files\articles\2008-08-08\9ebfc925-c14f-43e7-8c89-9e1526bd3927.txt',
 '#UEFA',2,2,1),
 ('Fieis catolicos peregriman ','este ano a pereginação dos catolicos sera em abril',4,4,'APPROVED','src\main\resources\application_files\articles\2008-08-08\9ebfc925-c14f-43e7-8c89-9e1526bd3927.txt',
 '#Fome#ONG#Alimentos',4,1,1),
 ('Sabe o que e a insónia?','neste artigo, conheça um pouco mais sobre a insonia',7,5,'APPROVED','articles/1/90-uo',
 '#Fome#ONG#Alimentos',7,1,2),
 ('Assembleia chumba novo OGE','O novo OGE Angolano foi reprovado',1,5,'APPROVED','articles/1/90-uo',
 '#Fome#ONG#Alimentos',1,1,2),
 ('artigo for avaliation teste','',2,2,'APPLIED','src\main\resources\application_files\articles\2008-08-08\9ebfc925-c14f-43e7-8c89-9e1526bd3927.txt',
 '#Fome#ONG#Alimentos',1,null,null),
 
 
 
('Finlândia é o país mais feliz no Mundo','este país tornou-se no melhor pais para se vicer',6,6,'APPROVED','articles/1/90-uo',
 '#Fome#Curiosidade#Internacional',6,3,1),
 ('Luta contra o crime no uige',null,5,7,'REFUSED','articles/1/90-uo',
 '#Fome#Policia#Crime',5,3,1),
 ('Portugal entra na OTAN','Portugal é agora o mais novo menbro da OTA',6,8,'APPLIED','articles/1/90-uo',
 '#Fome#ONG#Alimentos',6,3,1),
 ('Cunene ultrapassa seca',null,5,9,'APPROVED','articles/1/90-uo',
 '#Fome#ONG#Alimentos',5,3,1),


('Falta de emprego obriga Jovens a emigrar','ha cada vez mais emigrantes Anglanos',5,17,'APPROVED','articles/1/90-uo',
 '#Fome#ONG#Alimentos',5,4,1),
 ('ENI abre nova refinaria','a petrolifera Eni prepara-se para abrir a sua nova',3,19,'APPROVED','articles/1/90-uo',
 '#Fome#Petroleo#Energia',3,4,1),
 
 ('Messi leva bota de ouro','messi conquistou a sua terceira consucutiva',2,21,'APPLIED','articles/1/90-uo',
 '#Futebol#Desporto',2,6,2),
 ('Assembleia Aprova nova lei','O parlamento aprovou uma nova lei sobre',1,24,'APPROVED','articles/1/90-uo',
 '#Povo#Politica',1,6,2),
 ('Policia Recruta novos militares',null,1,25,'APPLIED','articles/1/90-uo',
 '#Policia#Emprego',1,6,1);




INSERT Into magazine_article(magazine_id,article_id,edition) values
(1,1,1),(1,3,1),
(1,4,2),(1,5,2),

(2,2,1),

(3,6,1),(3,7,1),(3,8,1),(3,9,1),

(4,10,1),(4,11,1),
(6,12,2),(6,13,2),(6,14,1);

insert into magazine_publication(date,edition,magazine_id) values
('2006-10-14',1,1),('2008-03-23',2,1)
,('2014-05-23',1,2)
,('2016-06-20',1,3)
,('2010-05-24',1,4);


insert into avaliation (mark,comment,journalist_id,article_id,date) values
(8,null,1,1,now()),(8,null,2,1,now()),(8,null,3,1,now()),(7,null,4,1,now()),
(10,null,1,2,now()),(10,null,2,2,now()),(10,null,3,2,now()),(10,null,4,2,now()),
(10,null,1,3,now()),(9,null,2,3,now()),(8,null,3,3,now()),(9,null,4,3,now()),
(8,null,1,4,now()),(10,null,2,4,now()),(10,null,3,4,now()),(10,null,4,4,now()),(10,null,5,4,now()),
(8,null,1,5,now()),(10,null,2,5,now()),(10,null,3,5,now()),(10,null,4,5,now()),(10,null,5,5,now()),

(7,null,6,6,now()),(8,null,7,6,now()),(8,null,8,6,now()),(7,null,9,6,now()),
(1,null,6,7,now()),(3,null,7,7,now()),(8,null,10,7,now()),(3,null,9,7,now()),
(8,null,6,8,now()),(8,null,7,8,now()),(8,null,8,8,now()),(7,null,10,8,now()),
(8,null,6,9,now()),(8,null,7,9,now()),(8,null,8,9,now()),(7,null,9,9,now()),(7,null,10,9,now()),

(7,null,16,10,now()),(8,null,18,10,now()),(8,null,19,10,now()),(7,null,20,10,now()),
(8,null,16,11,now()),(8,null,17,11,now()),(8,null,18,11,now()),(7,null,20,11,now()),

(7,null,24,12,now()),(8,null,25,12,now()),
(8,null,21,13,now()),(8,null,22,13,now()),(8,null,23,13,now()),(7,null,25,13,now()),
(8,null,24,14,now());


