drop table if exists user;
create table user (
    id char(32) not null,
    username varchar(255),
    real_name varchar(255),
    age int,
    about varchar(2000),
    encrypted_password varchar(255),
    role char(32),
    email varchar(255),
    city varchar(255),
    image_url varchar(1000),
    country varchar(255),
    coins_count int,
    make varchar(255),
    model varchar(255),
    year varchar(255),
    car_image_url varchar(2000),
    car_description varchar(2000),
    primary key (id));

drop table if exists publication;
create table publication (
    id char(32) not null,
    text text,
    user_id char(32) not null,
    title varchar(255),
    subtitle varchar(255),
    is_published boolean not null,
    created_date timestamp,
    updated_date timestamp,
    rating int,
    category varchar(255),
    image_url varchar(1000),
    primary key (id),
    constraint fk_publication_user_id foreign key (user_id) references user (id) on delete cascade);

drop table if exists publication_vote;
create table publication_vote (
    user_id char(32) not null,
    publication_id char(32) not null,
    primary key (user_id, publication_id));
