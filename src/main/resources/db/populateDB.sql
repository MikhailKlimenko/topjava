DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id,date_time,description,calories) VALUES
(100000,'2018-11-5 10:23:54','Завтрак',500),
(100000,'2018-11-5 10:23:54','Обед',1000),
(100000,'2018-11-5 10:23:54','Ужин',500);