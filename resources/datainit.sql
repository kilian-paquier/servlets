INSERT INTO admin(id, FirstName, Name, Login, Password) VALUES (1,'nathan','couton','nath','pass');
INSERT INTO admin(id, FirstName, Name, Login, Password) VALUES (2,'vincent','pozzi','vince','pass');

INSERT INTO candidate(id, FirstName, Name, Login, Password, birthDate, city, vote, party)
VALUES (1,'kilian', 'paquier', 'kiki', 'pass', '1997-01-01', 'poitiers', 0, 'kikipresident');
INSERT INTO candidate(id, FirstName, Name, Login, Password, birthDate, city, vote, party)
VALUES (2,'aaaa', 'bbbbb', 'abab', 'pass', '1997-01-01', 'tours', 0, 'kikipresident');

INSERT INTO party(partyName, Headquarters, NbCandidates) VALUES ('kikipresident','bretagne', 2);

INSERT INTO voter(id, FirstName, Name, Login, Password, birthDate, city, vote)
VALUES (1,'jhjh','kjkj', 'log', 'pass', '1997-01-01', 'tours', 0);
INSERT INTO voter(id, FirstName, Name, Login, Password, birthDate, city, vote)
VALUES (2, 'hfhfh', 'tht', 'log', 'pass', '1997-01-01', 'tours', 0);

