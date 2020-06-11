drop database if exists techno_db;
create database techno_db CHARACTER SET utf8 COLLATE utf8_general_ci;

use techno_db;

create table store
(
    id        int primary key auto_increment,
    name      varchar(50)  not null,
    store_url varchar(255) not null
);

create table link_product
(
    id           int auto_increment primary key,
    store_id     int          not null,
    product_type varchar(50)  not null,
    link         varchar(255) not null,
    constraint link_product_store_fk
        foreign key (store_id) references store (id)
);

create table phone
(
    id       int auto_increment primary key,
    title    varchar(255) not null,
    price    int          not null,
    url      varchar(255) not null,
    img_url  varchar(255) not null,
    store_id int          not null,
    constraint phone_store_fk
        foreign key (store_id) references store (id)
);

create table laptop
(
    id       int auto_increment primary key,
    title    varchar(255) not null,
    price    int          not null,
    url      varchar(255) not null,
    img_url  varchar(255) not null,
    store_id int          not null,
    constraint laptop_store_fk
        foreign key (store_id) references store (id)
);

create table tv_set
(
    id       int auto_increment primary key,
    title    varchar(255) not null,
    price    int          not null,
    url      varchar(255) not null,
    img_url  varchar(255) not null,
    store_id int          not null,
    constraint tv_set_store_fk
        foreign key (store_id) references store (id)
);

create table old_data_product
(
    id           int auto_increment primary key,
    title        varchar(255) not null,
    price        int          not null,
    url          varchar(255) not null,
    img_url      varchar(255) not null,
    store_id     int          not null,
    product_type varchar(20)
);

insert into store (name, store_url)
values ('foxtrot', 'https://www.foxtrot.com.ua/');

insert into link_product (store_id, product_type, link)
values ((select id from store where name = 'foxtrot'), 'phone',
        'https://www.foxtrot.com.ua/ru/shop/mobilnye_telefony_smartfon.html'),
       ((select id from store where name = 'foxtrot'), 'laptop', 'https://www.foxtrot.com.ua/ru/shop/noutbuki.html'),
       ((select id from store where name = 'foxtrot'), 'tv_set',
        'https://www.foxtrot.com.ua/ru/shop/led_televizory.html');

insert into store (name, store_url)
values ('comfy', 'https://www.comfy.ua/');

insert into link_product (store_id, product_type, link)
values ((select id from store where name = 'comfy'), 'phone', 'https://comfy.ua/smartfon/'),
       ((select id from store where name = 'comfy'), 'laptop', 'https://comfy.ua/notebook/'),
       ((select id from store where name = 'comfy'), 'tv_set', 'https://comfy.ua/flat-tvs/');

create table user_role
(
    id   int auto_increment primary key,
    role varchar(20) not null
);

insert into user_role(role)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

create table user_table
(
    id           int auto_increment primary key,
    name         varchar(50)  not null,
    birth_day    date         not null,
    login        varchar(50)  not null,
    password     varchar(255) not null,
    phone_number bigint          not null,
    email        varchar(255) not null,
    create_date  timestamp    not null
);

create table user_role_table
(
    id      int auto_increment primary key,
    user_id int not null,
    role_id int not null,
    constraint user_table_user_role_table_fk
        foreign key (user_id) references user_table (id),
    constraint user_role_user_role_table_fk
        foreign key (role_id) references user_role (id)
);

create table user_subscription
(
    id           int primary key auto_increment,
    user_id      int         not null,
    product_id   int         not null,
    product_type varchar(50) not null
);

insert into user_table (name, birth_day, login, password, phone_number, email)
values ('Сергей', '1991-08-06', 'test', '$2a$10$D9t1mnWmMQJkBnUXrEolAOy8k2GH2tjkJu8kh0qanj0wyIAIgBYT6', '380637916026',
        'orlov@mail.com');

insert into user_role_table (user_id, role_id)
values (1, 1),
       (1, 2);