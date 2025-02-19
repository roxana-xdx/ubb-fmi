create database librarie

create table carte(
id_car int primary key,
ISBN int,
titlu varchar(50),
an_aparitie int
)

create table cititor(
id_cit int primary key,
nume varchar(50)
)

create table achizitie(
id_car int foreign key references carte(id_car) on delete cascade on update cascade,
id_cit int foreign key references cititor(id_cit) on delete cascade on update cascade,
constraint pk_achizitie primary key(id_car, id_cit),
data_achizitie int
)

create table autor(
id_a int primary key,
nume varchar(50),
an_nastere int
)

create table publicare(
id_car int foreign key references carte(id_car) on delete cascade on update cascade,
id_a int foreign key references autor(id_a) on delete cascade on update cascade,
constraint pk_publicare primary key(id_car, id_a),
data_publicare int
)

create table recenzie(
id_r int primary key,
autor varchar(50),
titlu varchar(50),
id_car int foreign key references carte(id_car) on delete cascade on update cascade
)

insert into carte values (1, 1234567, 'Un veac de singuratate', 1967)
insert into carte values (2, 2345678, 'Ne vedem in august', 2024)
insert into carte values (3, 3456789, 'Maestrul si Margareta', 1967)

insert into cititor values (1, 'Narcisa')
insert into cititor values (2, 'Violeta')
insert into cititor values (3, 'Floarea')

insert into achizitie values (1, 2, 2014)
insert into achizitie values (1, 1, 2022)
insert into achizitie values (2, 1, 2016)
insert into achizitie values (2, 2, 2010)
insert into achizitie values (2, 3, 2010)

insert into autor values (1, 'Gabriel Garcia Marquez', 1927)
insert into autor values (2, 'Simone de Beauvoir', 1908)
insert into autor values (3, 'Mihail Bulgakov', 1891)

insert into publicare values (1, 1, 2000)
insert into publicare values (2, 1, 2005)
insert into publicare values (3, 3, 2000)

insert into recenzie values (1, 'Mihai', 'O recenzie', 1)
insert into recenzie values (2, 'Ion', 'Alta recenzie', 2)
insert into recenzie values (3, 'Ana', 'Despre opera lui Gabriel García Márquez', 1)

update recenzie set autor='Andrei' where id_r > 0
update publicare set data_publicare=2002 where id_car is not null

delete from achizitie where data_achizitie = 2014 or data_achizitie = 2016


select c.titlu as titlu_carte, c.an_aparitie as an_aparitie_carte, r.titlu as titlu_recenzie from carte c
inner join recenzie r on c.id_car = r.id_car
where c.titlu = 'Un veac de singuratate'
union
select c.titlu, c.an_aparitie, r.titlu from carte c
inner join recenzie r on c.id_car = r.id_car
where c.titlu = 'Ne vedem in august'

select c.titlu, a.data_achizitie, ci.nume as nume_client from carte c
inner join achizitie a on c.id_car = a.id_car
inner join cititor ci on a.id_cit = ci.id_cit
where ci.nume = 'Narcisa' or ci.nume = 'Violeta'

select c.titlu, a.nume, p.data_publicare from carte c
inner join publicare p on c.id_car = p.id_car
inner join autor a on p.id_a = a.id_a

select distinct a.nume, p.data_publicare from publicare p
inner join autor a on p.id_a = a.id_a

select c.titlu, a.data_achizitie, ci.nume as nume_client from carte c
left join achizitie a on c.id_car = a.id_car
left join cititor ci on a.id_cit = ci.id_cit

select a.id_car, count(*) as exemplare_vandute from achizitie a
inner join carte c on a.id_car = c.id_car
where c.titlu = 'Ne vedem in august'
group by a.id_car

select c.nume from cititor c
inner join achizitie a on a.id_cit = c.id_cit
group by c.nume
having min(data_achizitie) = 2010

select a.id_car, count(*) as exemplare_vandute from achizitie a
group by a.id_car
having max(data_achizitie) = 2016