create table persistent_logins (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);

create table actor (
    actor_id bigint not null auto_increment,
    first_name varchar(255),
    gender varchar(255),
    last_name varchar(255),
    film_id bigint,
    primary key (actor_id)) engine=InnoDB;

create table address (
    address_id bigint not null auto_increment,
    postal_code integer,
    region varchar(255),
    street_address varchar(255),
    cinema_id bigint,
    city_id bigint,
    contact_id bigint,
    primary key (address_id)) engine=InnoDB;

create table booking (
    booking_id bigint not null auto_increment,
    create_date datetime,
    pay bit,
    primary key (booking_id)) engine=InnoDB;

create table cinema (
    cinema_id bigint not null auto_increment,
    active bit,
    description text,
    logo_image varchar(255),
    main_image varchar(255) not null,
    name varchar(255) not null,
    rules varchar(255),
    upper_banner_image varchar(255),
    seos_id bigint,
    primary key (cinema_id)) engine=InnoDB;

create table cinema_images (
    cinema_id bigint not null,
    images varchar(255)) engine=InnoDB;

create table city (
    city_id bigint not null auto_increment,
    name varchar(30) not null,
    country_country_id bigint,
    primary key (city_id)) engine=InnoDB;

create table contact (
    contact_id bigint not null auto_increment,
    phone integer,
    user_id bigint,
    primary key (contact_id)) engine=InnoDB;

create table contact_contact_address (
    contact_contact_id bigint not null,
    contact_address_address_id bigint not null) engine=InnoDB;

create table country (
    country_id bigint not null auto_increment,
    name varchar(30) not null,
    primary key (country_id)) engine=InnoDB;

create table details (
    details_id bigint not null auto_increment,
    avatar varchar(255),
    birthday date,
    first_name varchar(255),
    gender varchar(255),
    language varchar(255),
    last_name varchar(255),
    user_id bigint,
    primary key (details_id)) engine=InnoDB;

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
    seos_id bigint,
    primary key (film_id)) engine=InnoDB;

create table film_image (
    film_image_id bigint not null auto_increment,
    image varchar(255),
    film_id bigint,
    primary key (film_image_id)) engine=InnoDB;

create table hall (
    hall_id bigint not null auto_increment,
    banner_image varchar(255),
    description text,
    name varchar(255),
    schema_image varchar(255),
    cinema_id bigint,
    seos_id bigint,
    primary key (hall_id)) engine=InnoDB;

create table hall_image (
    hall_image_id bigint not null auto_increment,
    image varchar(255),
    hall_id bigint,
    primary key (hall_image_id)) engine=InnoDB;

create table news (
    news_id bigint not null auto_increment,
    active bit,
    creation_date datetime,
    description text,
    main_image varchar(255),
    name varchar(255),
    publish_date datetime,
    video_link varchar(255),
    seo_id bigint,
    primary key (news_id)) engine=InnoDB;

create table news_image (
    news_image_id bigint not null auto_increment,
    image varchar(255),
    news_id bigint,
    primary key (news_image_id)) engine=InnoDB;

create table page (
    page_id bigint not null auto_increment,
    active bit,
    creation_date datetime,
    description text,
    main_image varchar(255),
    name varchar(255),
    seo_id bigint,
    primary key (page_id)) engine=InnoDB;

create table page_image (
    page_image_id bigint not null auto_increment,
    image varchar(255),
    page_id bigint,
    primary key (page_image_id)) engine=InnoDB;

create table place (
    place_id bigint not null auto_increment,
    active bit,
    row integer,
    seats integer,
    hall_id bigint,
    primary key (place_id)) engine=InnoDB;
create table promotion (promotion_id bigint not null auto_increment, active bit, creation_date datetime, description text, main_image varchar(255), name varchar(255), publish_date datetime, video_link varchar(255), seo_id bigint, primary key (promotion_id)) engine=InnoDB;
create table promotion_image (promotion_image_id bigint not null auto_increment, image varchar(255), promotion_id bigint, primary key (promotion_image_id)) engine=InnoDB;
create table role_type (id bigint not null auto_increment, code varchar(255) not null, name varchar(255), primary key (id)) engine=InnoDB;
create table seance (seance_id bigint not null auto_increment, date date, time time, cinema_id bigint, film_id bigint, hall_id bigint, primary key (seance_id)) engine=InnoDB;
create table secure_token (id bigint not null auto_increment, expire_at datetime not null, time_stamp datetime, token varchar(255), customer_id bigint, primary key (id)) engine=InnoDB;
create table seo (seo_id bigint not null auto_increment, description text, keywords varchar(255), title varchar(255), url varchar(255), primary key (seo_id)) engine=InnoDB;
create table ticket (ticket_id bigint not null auto_increment, create_ticket datetime, is_booked bit, price integer, booking_id bigint, seance_id bigint, primary key (ticket_id)) engine=InnoDB;
create table user (id bigint not null auto_increment, account_verified bit, email varchar(255), failed_login_attempts integer, first_name varchar(255), last_name varchar(255), login_disabled bit, password varchar(255), token varchar(255), primary key (id)) engine=InnoDB;
create table user_groups (customer_id bigint not null, group_id bigint not null, primary key (customer_id, group_id)) engine=InnoDB;
create table user_user_contact (user_entity_id bigint not null, user_contact_contact_id bigint not null) engine=InnoDB;
create table user_user_details (user_entity_id bigint not null, user_details_details_id bigint not null) engine=InnoDB;

alter table contact_contact_address add constraint UK_p8ui5bmp1pmn90x5pvt50du6a unique (contact_address_address_id);
alter table role_type add constraint UK_io68jsmlc972yakrq0do3uus5 unique (code);
alter table secure_token add constraint UK_1vnojtqwxyii8kinnsohdknhw unique (token);
alter table user add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user_user_contact add constraint UK_t7unyxkhb4udvhnb3g3on9eh1 unique (user_contact_contact_id);
alter table user_user_details add constraint UK_q8c1ie9bhcwrvkifofg57a1k unique (user_details_details_id);
alter table actor add constraint FK9g5yep1i8y4jmyf20hluomdvi foreign key (film_id) references film (film_id);
alter table address add constraint FKqudc0k9tadm6qh4osayoh6cx3 foreign key (cinema_id) references cinema (cinema_id);
alter table address add constraint FKpo044ng5x4gynb291cv24vtea foreign key (city_id) references city (city_id);
alter table address add constraint FK660ac8r9vntokuyh6agtj8pkh foreign key (contact_id) references contact (contact_id);
alter table cinema add constraint FK19ikit6xpt3akertpg0vm7hkk foreign key (seos_id) references seo (seo_id);
alter table cinema_images add constraint FKruaty3jb5unrm8mv4mscahr90 foreign key (cinema_id) references cinema (cinema_id);
alter table city add constraint FKeva9o92w3m9d6pj3byt08qrx9 foreign key (country_country_id) references country (country_id);
alter table contact add constraint FKe07k4jcfdophemi6j1lt84b61 foreign key (user_id) references user (id);
alter table contact_contact_address add constraint FKcqf7qxj4idas5bsmxe899xhu0 foreign key (contact_address_address_id) references address (address_id);
alter table contact_contact_address add constraint FKcb5emkgac2457jog6i94m96eg foreign key (contact_contact_id) references contact (contact_id);
alter table details add constraint FK4nofffqdc6o09cect5lwxyjf2 foreign key (user_id) references user (id);
alter table film add constraint FK8mkwfrgc46e20du3m0gr1evmv foreign key (seos_id) references seo (seo_id);
alter table film_image add constraint FK5b24j4h1wq9yp1ythbkxwnjie foreign key (film_id) references film (film_id);
alter table hall add constraint FKte75ikgkdmhfutuupvx2lhknr foreign key (cinema_id) references cinema (cinema_id);
alter table hall add constraint FK5y3m1i2vxly44yo7wd5n15ke3 foreign key (seos_id) references seo (seo_id);
alter table hall_image add constraint FKlam285smfu6djw1w1n6p1gfue foreign key (hall_id) references hall (hall_id);
alter table news add constraint FK6bp62a0a7bs2lgyvp5rmucst2 foreign key (seo_id) references seo (seo_id);
alter table news_image add constraint FKhwajir19rbhvdif5as4g6wcwf foreign key (news_id) references news (news_id);
alter table page add constraint FKfmqij1l5l87xrkvej0dk32t4b foreign key (seo_id) references seo (seo_id);
alter table page_image add constraint FKencjs4bkar23r0040n8v96o3k foreign key (page_id) references page (page_id);
alter table place add constraint FKi6xueble16lcu6ftbkb0jf0mf foreign key (hall_id) references hall (hall_id);
alter table place add constraint FKbed1ktq5ep8fpk5s8q63jvu5l foreign key (place_id) references hall (hall_id);
alter table promotion add constraint FKjfw9k1qi4nkj0cffvokmkg5x0 foreign key (seo_id) references seo (seo_id);
alter table promotion_image add constraint FK1kk51vlapwvuxvgsptcnehh70 foreign key (promotion_id) references promotion (promotion_id);
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
alter table user_user_contact add constraint FKnwgcrdxrulqti82lsgnqn23o0 foreign key (user_contact_contact_id) references contact (contact_id);
alter table user_user_contact add constraint FKqwfagcifayhd7al6y874q0nr0 foreign key (user_entity_id) references user (id);
alter table user_user_details add constraint FK6eg5fxa404htbv98ik34jjff8 foreign key (user_details_details_id) references details (details_id);
alter table user_user_details add constraint FKqpamb4hn8vfun9ux0y181bkq foreign key (user_entity_id) references user (id);