insert into role_type (id, code, name)
VALUES
       (1, 'Customer', 'Customer group'),
       (2, 'Admin', 'Admin group');

INSERT INTO user (id, account_verified, email, failed_login_attempts, first_name, last_name, login_disabled, password, token)
VALUES (1,
        true,
        'dovbushb@gmail.com',
        0,
        'Bohdan',
        'Dovbush',
        false,
        '{bcrypt}$2a$10$4YibHkqtm1paLxBrptMJdu5DM0ZBHL2S9FgNa.0cZVth9gUciNsUS',
        null);

INSERT INTO demo.user_groups (customer_id, group_id)
VALUES (1, 2);

INSERT INTO cinema (cinema_id, active, description, logo_image, main_image, name, rules, upper_banner_image, seos_id)
VALUES (5, null, 'dxfsdf', 'b93c22cf-ba34-449e-88e4-7abe904c6642.Знімок екрана 2020-05-01 о 21.48.27.png', '9a5699ed-154e-419e-83c4-fcd9afc0b24c.Знімок екрана 2020-04-22 о 15.45.29.png', 'csdfsdf', 'sdfsdfsdf', '780a085e-9a1e-4ba5-8b92-15df3a290949.Знімок екрана 2020-05-02 о 00.08.43.png', null),
       (8, null, 'dfsdfsf', 'bf437932-1b3b-4d76-ac12-fc3bd5ee742b.Знімок екрана 2020-04-22 о 14.47.21.png', '5c599791-c51d-46d5-bc27-b0983a467080.Знімок екрана 2020-04-22 о 14.47.21.png', 'sfsdfs', 'fdsfsdf', 'c916cff8-0cb7-4c9c-b338-296e04c428e7.Знімок екрана 2020-04-22 о 14.47.21.png', null);
INSERT INTO demo.seo (seo_id, description, keywords, title, url)
VALUES (5, 'description', 'keywords', 'title', 'url'),
        (6, 'description', 'keywords', 'title', 'url'),
        (9, 'description', 'keywords', 'title', 'url');

INSERT INTO demo.cinema_images (cinema_id, images)
VALUES (5, '0f21c07a-173f-46fa-b68b-dc81818ad6cd.Знімок екрана 2020-05-11 о 20.23.31.png'),
        (8, '3b000566-630c-4e8e-897d-487ecb64aae3.Знімок екрана 2020-04-22 о 14.47.21.png');

