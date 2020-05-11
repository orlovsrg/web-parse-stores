use techno_db;

create table store
(
    id        int primary key auto_increment,
    name      varchar(50)  not null,
    store_url varchar(255) not null
);

insert into store (name, store_url)
values ('foxtrot', 'https://www.foxtrot.com.ua/');

insert into store (name, store_url)
values ('comfy', 'https://www.comfy.ua/');

create table selector_key
(
    id           int auto_increment primary key,
    store_id     int          not null,
    product_type varchar(50)  not null,
    selector_key varchar(255) not null,
    constraint selector_key_store_fk
        foreign key (store_id) references store (id)
);

insert into selector_key (store_id, product_type, selector_key)
values ((select id from store where name = 'foxtrot'), 'phone', 'https://www.foxtrot.com.ua/ru/shop/mobilnye_telefony_smartfon.html'),
       ((select id from store where name = 'foxtrot'), 'laptop', 'https://www.foxtrot.com.ua/ru/shop/noutbuki.html'),
       ((select id from store where name = 'foxtrot'), 'tv_set', 'https://www.foxtrot.com.ua/ru/shop/led_televizory.html');

insert into selector_key (store_id, product_type, selector_key)
values ((select id from store where name = 'comfy'), 'phone', 'https://comfy.ua/smartfon/'),
       ((select id from store where name = 'comfy'), 'laptop', 'https://comfy.ua/notebook/'),
       ((select id from store where name = 'comfy'), 'tv_set', 'https://comfy.ua/flat-tvs/');

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

create table user_role(
  id int auto_increment primary key,
  role varchar(20) not null
);

insert into user_role(role) values ('ROLE_ADMIN'), ('ROLE_USER');

create table user_table (
    id int auto_increment primary key ,
    name varchar(50) not null ,
    birth_day date not null ,
    login varchar(50) not null ,
    password varchar(50) not null ,
    phone_number int not null ,
    email varchar(255) not null ,
    role_id int not null ,
    create_date timestamp,
    constraint user_table_role_fk
            foreign key (role_id) references user_role(id)
);