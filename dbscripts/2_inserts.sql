use ticket_core_db;

alter table ticket_core_db.user MODIFY column category_id INT NULL;

insert into ticket_core_db.role values (NULL, 'client', 'Клиент');
insert into ticket_core_db.role values (NULL, 'expert', 'Эксперт');

insert into ticket_core_db.category values (NULL, 'Бухгалтерия', 0);

insert into ticket_core_db.user (id, username, password, category_id, role_id, is_disabled, is_verified) values
 (NULL, 'client', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnQiLCJ1c2VySWQiOiIzIiwicm9sZSI6ItCa0LvQuNC10L3RgiJ9.wWCSocRbLcKduAwCvUJNy1-URL16GduKhCBjhgE4wE5vvpgkLdZ9BeexDiZQrhLUZtMmC9v-uZKrlTkyDn8M_Q', NULL, 1, 0, 0), 
 (NULL, 'expert', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnQiLCJ1c2VySWQiOiIzIiwicm9sZSI6ItCa0LvQuNC10L3RgiJ9.wWCSocRbLcKduAwCvUJNy1-URL16GduKhCBjhgE4wE5vvpgkLdZ9BeexDiZQrhLUZtMmC9v-uZKrlTkyDn8M_Q', 1, 2, 0, 0);

