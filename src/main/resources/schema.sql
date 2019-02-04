drop table if exists user;
create table user (
    id char(32) not null,
    username varchar(255),
    encrypted_password varchar(255),
    role char(32),
    email varchar(255),
    city varchar(255),
    image_url varchar(255),
    country varchar(255),
    coins_count int,
    primary key (id));

drop table if exists publication;
create table publication (
    id char(32) not null,
    text text,
    user_id char(32) not null,
    title varchar(255),
    is_published boolean not null,
    created_date timestamp,
    updated_date timestamp,
    rating int,
    category varchar(255),
    image_url varchar(255),
    primary key (id),
    constraint fk_publication_user_id foreign key (user_id) references user (id) on delete cascade);

drop table if exists publication_vote;
create table publication_vote (
    user_id char(32) not null,
    publication_id char(32) not null,
    primary key (user_id, publication_id));
