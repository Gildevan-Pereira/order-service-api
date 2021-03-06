# order-service-api
Desafio Spring Boot

Execute antes de criar as tabelas:

DROP EXTENSION IF EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

Depois crie o banco de dados

Banco de dados:

create table service_order(
	id serial4 not null primary key,
	uid uuid not null default uuid_generate_v4(),
	created_at timestamp not null default now(),
	removed_at timestamp,
	service_id serial4 not null,
	client_id serial4 not null,
	technician_id serial4 not null,
	started_at timestamp,
	finished_at timestamp,
	remarks text,
	
	foreign key (service_id) references service (id),
	foreign key (client_id) references client (id),
	foreign key (technician_id) references technician (id)
);

create table service_category(
	id serial4 not null primary key,
	uid uuid not null default uuid_generate_v4(),
	created_at timestamp not null default now(),
	removed_at timestamp,
	name varchar(60) not null
);

create table service(
	id serial4 not null primary key,
	uid uuid not null default uuid_generate_v4(),
	created_at timestamp not null default now(),
	removed_at timestamp,
	service_category_id serial4 not null,
	title varchar (50) not null,
	description text,
	amount numeric (9,2) not null,
	remarks text,
	
	foreign key (service_category_id) references service_category (id)
);

create table technician(
	id serial4 not null primary key,
	uid uuid not null default uuid_generate_v4(),
	created_at timestamp not null default now(),
	removed_at timestamp,
	fullname varchar (150) not null,
	identity varchar not null,
	phone varchar (16) not null,
	email varchar (75) not null,
	role varchar not null,
	commission numeric not null,
	address_id serial4 not null,
	
	foreign key (address_id) references address (id)
); 

create table client(
	id serial4 not null primary key,
	uid uuid not null default uuid_generate_v4(),
	created_at timestamp not null default now(),
	removed_at timestamp,
	fullname varchar (150) not null,
	identity varchar not null,
	phone varchar (10) not null,
	email varchar (75) not null,
	address_id integer not null,
	foreign key (address_id) references address (id)
);

create table address(
	id serial4 not null primary key,
	uid uuid not null default uuid_generate_v4(),
	created_at timestamp not null default now(),
	removed_at timestamp,
	street varchar (100) not null,
	number varchar not null,
	district varchar (50) not null,
	zipcode varchar (8) not null,
	city varchar (100) not null,
	state varchar (2) not null,
	complement varchar (150) not null
);
