use ticket_core_db;

alter table ticket_core_db.user MODIFY column category_id INT NULL;

insert into ticket_core_db.role values (NULL, 'client', 'Клиент');
insert into ticket_core_db.role values (NULL, 'expert', 'Эксперт');

insert into ticket_core_db.category values (NULL, 'Бухгалтерия', 0);

insert into ticket_core_db.user (id, username, password, category_id, role_id, is_disabled, is_verified) values
 (NULL, 'client', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnQiLCJ1c2VySWQiOiIzIiwicm9sZSI6ItCa0LvQuNC10L3RgiJ9.wWCSocRbLcKduAwCvUJNy1-URL16GduKhCBjhgE4wE5vvpgkLdZ9BeexDiZQrhLUZtMmC9v-uZKrlTkyDn8M_Q', NULL, 1, 0, 0),
 (NULL, 'expert', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjbGllbnQiLCJ1c2VySWQiOiIzIiwicm9sZSI6ItCa0LvQuNC10L3RgiJ9.wWCSocRbLcKduAwCvUJNy1-URL16GduKhCBjhgE4wE5vvpgkLdZ9BeexDiZQrhLUZtMmC9v-uZKrlTkyDn8M_Q', 1, 2, 0, 0);


use ticket_core_db;
ALTER TABLE ticket ADD user_id int NOT NULL;
ALTER TABLE ticket
ADD CONSTRAINT ticket_user_id_fk
FOREIGN KEY (user_id) REFERENCES user (id);
drop table offer;
ALTER TABLE offer_user CHANGE offer_id ticket_id int(11) NOT NULL;
ALTER TABLE offer_user DROP FOREIGN KEY offer_user_ibfk_1;
ALTER TABLE offer_user
ADD CONSTRAINT offer_user_ibfk_1
FOREIGN KEY (ticket_id) REFERENCES ticket (id);
rename table offer_user to offer;

ALTER TABLE offer ADD offer_status_id int NOT NULL;
ALTER TABLE offer
ADD CONSTRAINT offer_offer_status_id_fk
FOREIGN KEY (offer_status_id) REFERENCES offer_status (id);