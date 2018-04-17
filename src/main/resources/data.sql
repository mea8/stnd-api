INSERT INTO USER (id, username) VALUES(1001,  'Paul');
INSERT INTO USER (id, username) VALUES(1002,  'James');
INSERT INTO USER (id, username) VALUES(1003,  'Piter');

INSERT INTO RELATION (user_id, watched_id) VALUES( 1001, 1002);
INSERT INTO RELATION (user_id, watched_id) VALUES( 1001, 1003);


INSERT INTO POST (id, user_id, text, created_on) VALUES(1001, 1001, 'first post', sysdate());
INSERT INTO POST (id, user_id, text, created_on) VALUES(1002, 1001, 'second post', sysdate());
INSERT INTO POST (id, user_id, text, created_on) VALUES(1003, 1002, 'test post', sysdate());
INSERT INTO POST (id, user_id, text, created_on) VALUES(1004, 1003, 'test post', sysdate());
INSERT INTO POST (id, user_id, text, created_on) VALUES(1005, 1003, 'test post', sysdate());
