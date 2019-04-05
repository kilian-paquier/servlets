INSERT INTO admin(id, first_name, last_name, Login, Password) VALUES (1,'nathan','couton','nath','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO admin(id, first_name, last_name, Login, Password) VALUES (2,'vincent','pozzi','vince','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
INSERT INTO admin(id, first_name, last_name, Login, Password) VALUES (3,'admin','admin','admin','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');

--
-- Les mots de passe sont : password
--

INSERT INTO candidate(id, first_name, last_name, Login, Password, birth_date, city, has_voted, party_name)
VALUES (1,'kilian', 'paquier', 'kiki', 'pass', '1997-01-01', 'poitiers', 0, 'kikipresident');
INSERT INTO candidate(id, first_name, last_name, Login, Password, birth_date, city, has_voted, party_name)
VALUES (2,'aaaa', 'bbbbb', 'abab', 'pass', '1997-01-01', 'tours', 0, 'kikipresident');

INSERT INTO party(party_name, Headquarters, nb_candidates) VALUES ('kikipresident','bretagne', 2);

INSERT INTO voter(id, first_name, last_name, Login, Password, birth_date, city, has_voted)
VALUES (1,'jhjh','kjkj', 'log', 'pass', '1997-01-01', 'tours', 0);
INSERT INTO voter(id, first_name, last_name, Login, Password, birth_date, city, has_voted)
VALUES (2, 'hfhfh', 'tht', 'log', 'pass', '1997-01-01', 'tours', 0);

