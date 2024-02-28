--drop schema tickets;
create schema if not exists tickets;

create table if not exists tickets.train
(
id serial,
number_train varchar(40), -- номер поезда
name_train varchar(80), -- наименование поезда
departure_point varchar(200),-- пункт отправления
date_departure timestamp, -- время отправления
destination_point varchar(200), -- пункт назначения
date_arrival timestamp, --время прибытия
number_wagons int,-- кол-во вагонов
begin_number varchar(40), --нумерация вагонов
datedelete timestamp,
datecreate timestamp
);
create index if not exists idx_train_id on tickets.train (id);
create index if not exists idx_number_train  on tickets.train (number_train);

-- станции drop table tickets.stations
create table if not exists tickets.stations
(
id serial,
pid_train int,
name_station varchar(200),
city varchar(200),
date_arrival timestamp, --время прибытия
date_departure timestamp, -- время отправления
parking int, -- стоянка поезда на станции в минутах
datedelete timestamp,
datecreate timestamp
);
create index if not exists idx_stations_id on tickets.stations (id);
create index if not exists idx_pid_train_station on tickets.stations (pid_train);
create index if not exists idx_name_station on tickets.stations (name_station);

-- вагоны
create table if not exists tickets.wagons
(
id serial,
pid_train int,
number_wagon varchar(20),
plase int, --колво мест в вагоне
type_wagon varchar(40), --тип вагона
class_wagon varchar(40), -- класс вагона  
price_ticket_beg numeric(15,2),
price_ticket_end numeric(15,2),
comment text,
datedelete timestamp,
datecreate timestamp
);
create index if not exists idx_wagons_id on tickets.wagons (id);
create index if not exists idx_pid_train_wagons on tickets.wagons (pid_train);
create index if not exists idx_number_wagon on tickets.wagons (number_wagon);

-- Распределение вагонов и мест по станциям
create table if not exists tickets.seat_station
(
id serial,
pid_train int,
pid_wagon int,
pid_station int,
seat int,  -- место
type_seat varchar(50),
price numeric(15,2),
comment text,
datedelete timestamp,
datecreate timestamp
);
create index if not exists idx_seat_station_id on tickets.seatStation (id);
create index if not exists idx_pid_train on tickets.seatStation (pid_train);
create index if not exists idx_pid_wagon on tickets.seatStation (pid_wagon);
create index if not exists idx_pid_station on tickets.seatStation (pid_station);


-- билеты
create table if not exists tickets.tickets_rgd
(
id serial,
pid_wagon int,
pid_train int,
pid_station int,
type_ticket varchar,
number_ticket varchar(40),
seat int,  -- место
type_seat varchar(50),
pid_passenger int,
price numeric(15,2),
cancellation_date timestamp, -- дата возврата билета
datecreate timestamp
);
create index if not exists idx_tickets_rgd_id on tickets.tickets_rgd (id);
create index if not exists idx_pid_train_tickets_rgd on tickets.tickets_rgd (pid_train);
create index if not exists idx_pid_wagons on tickets.tickets_rgd (pid_wagon);
create index if not exists idx_pid_passenger on tickets.tickets_rgd (pid_passenger);
create index if not exists idx_number_ticket on tickets.tickets_rgd (number_ticket);

-- пассажиры
create table if not exists tickets.passengers
(
id serial, 
lastname varchar(40),
firstname varchar(40),
patronymic varchar(40),
birthday date,
gender int,
type_doc int,
ser_doc varchar(30),
num_doc varchar(30),
ctzn_oksm varchar(5),
datedelete timestamp,
datecreate timestamp
);
create index if not exists idx_passengers_id on tickets.passengers (id);
create index if not exists idx_assengers on tickets.passengers (lastname, firstname,patronymic,birthday,ser_doc,num_doc);

create table if not exists 
tickets.logError
(
id serial,
serviceName varchar(50),
idError uuid,
MessageError text,
dateOfCreate timestamp
); 

create table if not exists 
tickets.loger
(
id_log serial,
date_z timestamp,
company varchar(250),
user_ir varchar(180),
login_ir varchar(60),
code varchar(50),
"comment" varchar(250),	
"module" varchar(150),
action_ir varchar(150),
id_sluch varchar(150)
);

create table if not exists 
tickets.access
(
id serial,
login varchar(100);
token varchar(400),
deletedate TIMESTAMP,
datecreate timestamp
);

insert into tickets.access (login,token,datecreate) values ('service gd','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzZXJ2aWNlIGdkIiwiaWF0IjoxNzA4NjM1NjAwLCJleHAiOjE4MDMzMzAwMDB9.eb3t8a5d9xnN-kUF5gIjWMLZuGki7mCBtNM0tYdeVF6QJTS_bklh8sz5TveIdGtst7fCvlngB9VuhWKDu61XuA', now());