insert into users
(username, password, enabled)
values
('roy', 'spring-android', true);

insert into users
(username, password, enabled)
values
('phil', 'spring-boot', true);

insert into authorities
(username, authority)
values
('roy', 'ROLE_USER');

insert into authorities
(username, authority)
values
('roy', 'ROLE_ADMIN');

insert into authorities
(username, authority)
values
('phil', 'ROLE_USER');