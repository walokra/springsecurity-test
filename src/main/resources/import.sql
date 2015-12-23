-- users
INSERT INTO users(users_id, username, password, enabled) VALUES (1, 'admin', 'admin', 1);
INSERT INTO users(users_id, username, password, enabled) VALUES (2, 'user', 'user', 1);

-- roles
INSERT INTO roles(roles_id, username, role) VALUES (1, 'admin', 'SEC_ADMIN');
INSERT INTO roles(roles_id, username, role) VALUES (2, 'user', 'SEC_USER');
INSERT INTO roles(roles_id, username, role) VALUES (3, 'admin', 'SEC_USER');