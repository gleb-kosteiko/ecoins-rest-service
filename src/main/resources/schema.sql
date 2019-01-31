drop table if exists user;
create table user (
    id char(32) not null,
    username varchar(255),
    encrypted_password varchar(255),
    role char(32),
    email varchar(255),
    city varchar(255),
    country varchar(255),
    coins_count int,
    primary key (id));

drop table if exists publication;
create table publication (
    id char(32) not null,
    message varchar(255),
    user_id char(32) not null,
    title varchar(255),
    is_published boolean not null,
    primary key (id),
    constraint fk_publication_user_id foreign key (user_id) references user (id) on delete cascade);

drop table if exists user_config;
create table user_config (
    id char(32) not null,
    user_id char(32) not null,
    cron_schedule varchar(255),
    primary key (id),
    constraint fk_user_config_user_id foreign key (user_id) references user (id) on delete cascade);
