create table people (
  id serial primary key,
  first_name varchar,
  last_name varchar
);

create table properties (
  id serial primary key,
  owner_id integer references people (id),
  address varchar,
  size numeric
);
