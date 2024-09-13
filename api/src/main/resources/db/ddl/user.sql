drop table todo_item;
drop table sys_user;
create table sys_user
(
    id         bigint auto_increment    primary key,
    first_name varchar(20) default '' not null,
    last_name  varchar(20)            null,
    password   varchar(32) default '' not null,
    email      varchar(50)            not null,
    avatar     varchar(255)           null,
    is_deleted tinyint(1)  default 0  not null,
    created_time        DATE,
    last_updated_time   DATE,
    constraint sys_user_pk  unique (email)
);

create table todo_item
(
    id          bigint auto_increment    primary key,
    user_id     bigint,
    created_time        DATE,
    last_updated_time   DATE,
    description varchar(255),
    is_done    tinyint(1)  default 0  not null,
    is_deleted tinyint(1)  default 0  not null,
    constraint fk_todo_item FOREIGN KEY (user_id) REFERENCES sys_user (id) ON DELETE RESTRICT
);
