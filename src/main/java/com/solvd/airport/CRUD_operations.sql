-- Inserts

insert into Countries values (1,"Poland","PL");

insert into Addresses values (1,"Warsaw","00-000","Zlota","7","10A",1);

insert into Addresses values (2,"Lodz","92-500","Polna","4","1",1);

insert into Addresses values (3,"Lodz","98-670","Lesna","7g","7",1);

insert into People values (1,"Jan",null,"Kowalski",1);

insert into People values (2,"Anna","Zuzanna","Nowak",2);

insert into Airports values (1,"Chopin Airport","11PL",1);

insert into Airports values (2,"Lublinek","25PL",2);

insert into Plane_Types values (1, "Passenger plane");

insert into Airlines values(1, "Lott", 1);

insert into Planes values (1, "1111PL", 80, 2002, 1, null, 1);

insert into Flying_Licences values(1,"10100AA", "2012-05-12", "2030-05-12",1);

insert into Flights values(1,"2025-02-01 12:00:00","2025-02-01 15:00:00", 1, 2, 1, 1,null);

-- Updates

update People 
	set second_name = "Jakub"
	where id = 1;

update Addresses 
	set city = "Wroclaw"
	where country_id = 1;

update Airlines
	set name = "LOT"
	where id = 1;

update Flying_Licences
	set licence_code = "22222B"
	where pilot_id = 1;

update Flights
	set start_time = "2025-02-2 13:00:00", landing_time = "2025-02-2 16:00:00"
	where plane_id = 1;

update Planes
	set registration = "555PL"
	where id = 1;

update Airports
	set code = "77PL"
	where id = 2;

update Airports
	set code = "53PL"
	where id = 1;

update Addresses
	set street = "Zielona"
	where id = 2;

update People
	set first_Name = "Ela"
	where id = 2;

-- 5 alter table

alter table People
rename column last_name to surname;

alter table Planes
modify registration VARCHAR(12);

alter table Flights
add index idx_start_airport (start_airport_id);

alter table Airlines
add constraint unique_airline_name unique (name);

alter table People
drop column second_name;

-- 1 big statement to join all tables in the database.

select 
f.id as flight_id,
f.start_time,
f.landing_time,
pl.registration as plane_registration,
pl_types.name as plane_type,
a.name as airline_name,
h.name as hangar_name,
air.name as start_airport_name,
air_dest.name as landing_airport_name,
pp.first_name as pilot_first_name,
fl.licence_code as pilot_licence_code,
ad.city as pilot_origin_city,
c.name as pilot_origin_country,
t.ticket_code,
tc.name as ticket_class_name
from Flights f
left join Planes pl on f.plane_id = pl.id
left join Plane_Types pl_types on pl.plane_type_id = pl_types.id
left join Hangars h on pl.hangar_id = h.id
left join Airlines a on pl.airlane_id = a.id
left join Airports air on f.start_airport_id = air.id
left join Airports air_dest on f.destination_airport_id = air_dest.id
left join People pp on f.first_pilot_id = pp.id
left join Flying_Licences fl on pp.id = fl.pilot_id
left join Addresses ad on pp.address_id = ad.id
left join Countries c on ad.country_id = c.id
left join Tickets t on f.id = t.flight_id
left join Ticket_Classes tc on t.ticket_class_id = tc.id; 

-- 5 statements with left, right, inner, outer joins

select * 
from People p left join Addresses a on p.address_id = a.id;

select f.id as flight_id,
p.first_name as first_pilot_name
from Flights f right join People p on f.first_pilot_id = p.id;

-- only pilot who is assigned to flight and flight that has pilot
select f.id as flight_id,
p.first_name as first_pilot_name
from Flights f inner join People p on f.first_pilot_id = p.id;

-- outer join
select *
from Addresses ad
left join Airports air on ad.id = air.address_id
union
select *
from Addresses ad
right join Airports air on ad.id = air.address_id;

-- inner join for same tables to compare
select *
from Addresses ad
inner join Airports air on ad.id = air.address_id;

-- 7 statements with aggregate functions and group by and without having

select flight_id, count(t.id) as number_of_tickets
from Tickets t
left join Flights f on t.flight_id = f.id
group by t.flight_id;

select plane_type_id, avg(number_of_seats) as avg_seats_number
from Planes p 
group by plane_type_id;

select h.name, count(p.id) as number_number_of_planes
from Hangars h 
left join Planes p on h.id = p.hangar_id
group by h.name;

select a.name as airport_name, count(f.id) as number_of_departures
from Airports a
left join Flights f on a.id = f.start_airport_id
group by airport_name;

select c.name as country, count(air.id) as number_of_airports
from Countries c
left join Addresses a on c.id = a.country_id
left join Airports air on a.id = air.address_id
group by c.name;

select a.name as airline_name, count(p.id) as number_of_planes
from Airlines a
left join Planes p on a.id = p.airlane_id
group by a.name;

select tc.name as ticket_class, count(t.id) as number_of_tickets
from Ticket_Classes tc
left join Tickets t on tc.id = t.ticket_class_id
group by tc.name;

-- 7 statements with aggregate functions and group by and with having

select a.name as airport_name, count(f.id) as number_of_departures
from Airports a
left join Flights f on a.id = f.start_airport_id
group by a.name
having  number_of_departures > 2;

select f.id as flight_id, count(t.id) as number_of_tickets
from Flights f
left join Tickets t on f.id = t.flight_id
group by f.id
having  number_of_tickets = 0;

select a.name as airline_name, count(p.id) as number_of_planes
from Airlines a
left join Planes p on a.id = p.airlane_id
group by a.name
having number_of_planes > 0;

select a.name as airport_name, count(f.id) as number_of_departures
from Airports a
left join Flights f on a.id = f.start_airport_id
group by a.name
having  number_of_departures > 0;

select a.name as airport_name, count(h.id) as number_of_hangars
from Airports a
left join Hangars h on a.id = h.airport_id
group by a.name
having  number_of_hangars = 0;

select c.name as country, count(air.id) as number_of_airports
from Countries c
left join Addresses a on c.id = a.country_id
left join Airports air on a.id = air.address_id
group by c.name
having number_of_airports > 0;

select plane_type_id, avg(number_of_seats) as avg_seats_number
from Planes p 
group by plane_type_id
having avg_seats_number < 100;

-- Deletions

delete from Flights where id = 1;

delete from Planes where id = 1;

delete from Airlines where id = 1;

delete from People where id = 2;

delete from Flying_Licences where pilot_id = 1;

delete from People where id = 1;

delete from Airports where id = 1;

delete from Addresses where id = 1;

delete from Plane_Types where id = 1;

delete from Airports where address_id = 2;

