
create table `actor` (
    `actor_id` bigint not null auto_increment,
    `first_name` varchar(255),
    `gender` varchar(255),
    `last_name` varchar(255),
    `film_id` bigint, primary key (actor_id)
);

create table address (
    address_id bigint not null auto_increment,
    postal_code integer,
    region varchar(255),
    street_address varchar(255),
    cinema_id bigint,
    city_id bigint,
    contact_id bigint, primary key (address_id)
);

create table booking (
    booking_id bigint not null auto_increment,
    create_date datetime,
    pay bit, primary key (booking_id)
);

create table cinema (
    cinema_id bigint not null auto_increment,
    active bit,
    description text,
    logo_image varchar(255),
    main_image varchar(255),
    name varchar(255),
    rules varchar(255),
    upper_banner_image varchar(255),
    seos_id bigint, primary key (cinema_id)
);

create table city (
    city_id bigint not null auto_increment,
    name varchar(30) not null,
    country_id bigint, primary key (city_id)
                  );

create table contact (
    contact_id bigint not null auto_increment,
    phone varchar(9),
    user_id bigint, primary key (contact_id)
                     );

create table country (
    country_id bigint not null auto_increment,
    name varchar(30) not null, primary key (country_id)
                     );

create table details (
    details_id bigint not null auto_increment,
    birthday date,
    first_name varchar(255),
    gender varchar(255),
    language varchar(255),
    last_name varchar(255),
    user_id bigint, primary key (details_id)
                     );

create table film (
    film_id bigint not null auto_increment,
    date_finish date,
    date_release date,
    description text,
    film_year date,
    genre varchar(255),
    language varchar(255),
    main_image varchar(255),
    name varchar(255),
    trailer_link varchar(255),
    type varchar(255),
    seos_id bigint, primary key (film_id)
                  );

create table hall (
    hall_id bigint not null auto_increment,
    banner_image varchar(255),
    description text,
    name varchar(255),
    schema_image varchar(255),
    cinema_id bigint,
    seos_id bigint, primary key (hall_id)
                  );

create table image (
    image_id bigint not null auto_increment,
    image varchar(255),
    cinema_id bigint,
    details_id bigint,
    film_id bigint,
    hall_id bigint, primary key (image_id)
                   );

create table news (
    id bigint not null auto_increment, primary key (id)
                  );

create table place (
    place_id bigint not null auto_increment,
    active bit,
    row integer,
    seats integer,
    hall_id bigint, primary key (place_id)
                   );

create table promotion (
    id bigint not null auto_increment, primary key (id)
                       );

create table role_type (
    id bigint not null auto_increment,
    code varchar(255) not null,
    name varchar(255), primary key (id)
                       );

create table seance (
    seance_id bigint not null auto_increment,
    date date,
    time time,
    cinema_id bigint,
    film_id bigint,
    hall_id bigint, primary key (seance_id)
                    );

create table secure_token (
    id bigint not null auto_increment,
    expire_at datetime not null,
    time_stamp datetime,
    token varchar(255),
    customer_id bigint, primary key (id)
                          );

create table seo (
    seo_id bigint not null auto_increment,
    description text,
    keywords varchar(255),
    title varchar(255),
    url varchar(255), primary key (seo_id)
                 );

create table ticket (
    ticket_id bigint not null auto_increment,
    create_ticket datetime,
    is_booked bit,
    price integer,
    booking_id bigint,
    seance_id bigint, primary key (ticket_id)
                    );

create table user (
    id bigint not null auto_increment,
    account_verified bit,
    email varchar(255) not null,
    failed_login_attempts integer,
    first_name varchar(255),
    last_name varchar(255),
    login_disabled bit,
    password varchar(255) not null,
    token varchar(255), primary key (id)
                  );

create table user_groups (
    customer_id bigint not null,
    group_id bigint not null, primary key (customer_id, group_id)
                         );

create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
                               );

alter table role_type add constraint UK_io68jsmlc972yakrq0do3uus5 unique (code);
alter table secure_token add constraint UK_1vnojtqwxyii8kinnsohdknhw unique (token);
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table actor add constraint FK9g5yep1i8y4jmyf20hluomdvi foreign key (film_id) references film (film_id);
alter table actor add constraint FKib7foypibu9n6sxbv53psv5re foreign key (actor_id) references film (film_id);
alter table address add constraint FKqudc0k9tadm6qh4osayoh6cx3 foreign key (cinema_id) references cinema (cinema_id);
alter table address add constraint FKpo044ng5x4gynb291cv24vtea foreign key (city_id) references city (city_id);
alter table address add constraint FK660ac8r9vntokuyh6agtj8pkh foreign key (contact_id) references contact (contact_id);
alter table address add constraint FKelqk7bsyfpos6fdldoegcef9w foreign key (address_id) references contact (contact_id);
alter table cinema add constraint FK19ikit6xpt3akertpg0vm7hkk foreign key (seos_id) references seo (seo_id);
alter table city add constraint FKrpd7j1p7yxr784adkx4pyepba foreign key (country_id) references country (country_id);
alter table city add constraint FKcme05gukpf4v5xelrx4polivf foreign key (city_id) references country (country_id);
alter table contact add constraint FKe07k4jcfdophemi6j1lt84b61 foreign key (user_id) references user (id);
alter table contact add constraint FKqgj7ka9h4nt6snuo4tpj7bh52 foreign key (contact_id) references user (id);
alter table details add constraint FK4nofffqdc6o09cect5lwxyjf2 foreign key (user_id) references user (id);
alter table details add constraint FKok64g3c40g9m3nu1708weesvv foreign key (details_id) references user (id);
alter table film add constraint FK8mkwfrgc46e20du3m0gr1evmv foreign key (seos_id) references seo (seo_id);
alter table hall add constraint FKte75ikgkdmhfutuupvx2lhknr foreign key (cinema_id) references cinema (cinema_id);
alter table hall add constraint FK5y3m1i2vxly44yo7wd5n15ke3 foreign key (seos_id) references seo (seo_id);
alter table hall add constraint FKnbcb6w7w7sg0g9pa4apfgnttv foreign key (hall_id) references cinema (cinema_id);
alter table image add constraint FKdfq30dtfaccuh803ydff42fh7 foreign key (cinema_id) references cinema (cinema_id);
alter table image add constraint FKeuxfifhagpyfvb1ajdxwd3flr foreign key (details_id) references details (details_id);
alter table image add constraint FK8avenu6fif8gnhmrp13kcudp0 foreign key (film_id) references film (film_id);
alter table image add constraint FKai2bej5sv90iat0ous078ckju foreign key (hall_id) references hall (hall_id);
alter table image add constraint FK5nf91p3gb9xu2lbx0yy7eig2s foreign key (image_id) references details (details_id);
alter table place add constraint FKi6xueble16lcu6ftbkb0jf0mf foreign key (hall_id) references hall (hall_id);
alter table place add constraint FKbed1ktq5ep8fpk5s8q63jvu5l foreign key (place_id) references hall (hall_id);
alter table seance add constraint FKp28kgyjrwguokjh8gngcvxy14 foreign key (cinema_id) references cinema (cinema_id);
alter table seance add constraint FKchlcmip8ejlfuo4c990k5ry8y foreign key (film_id) references film (film_id);
alter table seance add constraint FKc33k6vbu1o9pneuqn6wius0ti foreign key (hall_id) references hall (hall_id);
alter table seance add constraint FKdlc80tinyerpcl3vsshmo21hn foreign key (seance_id) references hall (hall_id);
alter table secure_token add constraint FKfptf8yfhbkbudeeqfvu8uu6xq foreign key (customer_id) references user (id);
alter table ticket add constraint FKrg7x158t96nucwslhq2bad6qm foreign key (booking_id) references booking (booking_id);
alter table ticket add constraint FK8dikts18gyohd5ind6ooffxj1 foreign key (seance_id) references seance (seance_id);
alter table ticket add constraint FKdqyx4timmnwp3v59972hfemcf foreign key (ticket_id) references booking (booking_id);
alter table user_groups add constraint FKmjx8pc13na72qvkscnvrlton foreign key (group_id) references role_type (id);
alter table user_groups add constraint FKt6bf937mtvbgcacyplxsgd1c0 foreign key (customer_id) references user (id);