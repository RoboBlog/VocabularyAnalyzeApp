# noinspectionSqlNoDataSourceInspectionForFile
INSERT INTO user (userid, username, password, enabled, last_password_reset_date, amount_known_words, amount_words, score, day_score, email) VALUES (2, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1, STR_TO_DATE('21,5,2013', '%d,%m,%Y'), 0, 0, 0, 0, 'maciek@gmail.com');
INSERT INTO authority (id, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, NAME) VALUES (2, 'ROLE_ADMIN');
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO information(id, author, content, create_date, is_active) VALUES (1, 'admin', 'v.0.0 TEST JEST DOBRZE', STR_TO_DATE('21,5,2013', '%d,%m,%Y'), true)