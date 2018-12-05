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