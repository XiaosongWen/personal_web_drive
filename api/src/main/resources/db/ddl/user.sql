drop table todo_item;
drop table sys_user;
create table sys_user
(
    id         uuid             primary key,
    first_name varchar(20) default '' not null,
    last_name  varchar(20)            null,
    password   varchar(32) default '' not null,
    email      varchar(50)            not null unique,
    avatar     varchar(255)           null,
    is_deleted boolean       default false  not null,
    created_time        DATE,
    last_updated_time   DATE
);

create table todo_item
(
    id          uuid    primary key,
    user_id     uuid  references sys_user(id),
    created_time        DATE,
    last_updated_time   DATE,
    description varchar(255),
    is_done    boolean  default false  not null,
    is_deleted boolean  default false  not null
);

