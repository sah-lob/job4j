create table if not exists items  (
   id serial primary key not null,
   name text,
   description text,
   date_of_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table if not exists comments (
	id serial primary key not null,
	comment text,
	item_id int
);

