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