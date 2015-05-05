# --- !Ups
create table catalog (
  id IDENTITY not null PRIMARY KEY,
  name VARCHAR(100) not null,
  manufacturer VARCHAR(200) not null
);

# --- !Downs
drop table catalog;