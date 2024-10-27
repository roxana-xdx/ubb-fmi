create database bookstore

create table book(
id_b int primary key,
ISBN int,
title varchar(50),
year_of_publication int
)

create table customer(
id_c int primary key,
c_name varchar(50)
)

create table purchase(
id_b int foreign key references book(id_b) on delete cascade on update cascade,
id_c int foreign key references customer(id_c) on delete cascade on update cascade,
constraint pk_purchase primary key(id_b, id_c),
purchase_date int
)

create table author(
id_a int primary key,
a_name varchar(50),
birth_year int
)

create table publication(
id_b int foreign key references book(id_b) on delete cascade on update cascade,
id_a int foreign key references author(id_a) on delete cascade on update cascade,
constraint pk_publication primary key(id_b, id_a),
publication_date int
)

create table review(
id_r int primary key,
author varchar(50),
title varchar(50),
id_b int foreign key references book(id_b) on delete cascade on update cascade
)

insert into book values (1, 1234567, 'One Hundred Years of Solitude', 1967)
insert into book values (2, 2345678, 'Of Love and Other Demons', 1994)
insert into book values (3, 3456789, 'The Second Sex', 1949)
insert into book values (4, 4567890, 'The Master and Margarita', 1967)

insert into customer values (1, 'Customer_1')
insert into customer values (2, 'Customer_2')

insert into purchase values (1, 2, 2014)
insert into purchase values (1, 1, 2022)
insert into purchase values (2, 1, 2016)

insert into author values (1, 'Gabriel García Márquez', 1927)
insert into author values (2, 'Simone de Beauvoir', 1908)
insert into author values (3, 'Mihail Bulgakov', 1891)

insert into publication values (1, 1, 2010)
insert into publication values (2, 1, 2012)

insert into review values (1, 'Reviewer_1', 'Literary Geography: One Hundred Years of Solitude', 1)
insert into review values (2, 'Reviewer_2', 'The Hidden Art within Of Love and Other Demons', 2)

update review set author='Reviewer_3' where id_r > 0
update publication set publication_date=2008 where id_b is not null

delete from purchase where purchase_date = 2014 or purchase_date = 2016