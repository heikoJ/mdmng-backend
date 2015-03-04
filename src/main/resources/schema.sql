create table mdm_table (
  id integer GENERATED BY DEFAULT AS IDENTITY primary key ,
  name varchar(100) not null,
  display_name varchar(255) not null
);

create table mdm_column (
  id integer GENERATED BY DEFAULT AS IDENTITY primary key,
  name varchar(100) not null,
  display_name varchar(255) not null,
  data_type varchar(100) not null,
  table_id integer,
  is_primary_key boolean default false
) ;




create table country (
  id integer generated by default as identity primary key,
  code varchar(2) not null,
  name varchar(100) not null
)  ;