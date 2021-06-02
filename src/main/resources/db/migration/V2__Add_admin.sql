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