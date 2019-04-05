INSERT INTO admin(id, first_name, last_name, Login, Password) VALUES (1,'nathan','couton','nath','pass');
INSERT INTO admin(id, first_name, last_name, Login, Password) VALUES (2,'vincent','pozzi','vince','pass');

INSERT INTO candidate(id, first_name, last_name, Login, Password, birth_date, city, has_voted, party_name)
VALUES (1,'kilian', 'paquier', 'kiki', 'pass', '1997-01-01', 'poitiers', 0, 'kikipresident');
INSERT INTO candidate(id, first_name, last_name, Login, Password, birth_date, city, has_voted, party_name)
VALUES (2,'aaaa', 'bbbbb', 'abab', 'pass', '1997-01-01', 'tours', 0, 'kikipresident');

INSERT INTO party(party_name, Headquarters, nb_candidates) VALUES ('kikipresident','bretagne', 2);

INSERT INTO voter(id, first_name, last_name, Login, Password, birth_date, city, has_voted)
VALUES (1,'jhjh','kjkj', 'log', 'pass', '1997-01-01', 'tours', 0);
INSERT INTO voter(id, first_name, last_name, Login, Password, birth_date, city, has_voted)
VALUES (2, 'hfhfh', 'tht', 'log', 'pass', '1997-01-01', 'tours', 0);

