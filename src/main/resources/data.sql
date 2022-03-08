INSERT INTO users(email, name, password, username) VALUES ('oscar@gmail.com', 'Oscar', '$2a$10$M1g.cXVvnZWpln49geWNVuuy3q3L.Go5ndZssN31o0bfGf0p6nY2q', 'oscar');
INSERT INTO users(email, name, password, username) VALUES ('admin@gmail.com', 'admin', '$2a$10$YH9/2jIfxMiDBQJnOtxH3uU9CGVXXxNRbVKDwO3qvEM/kO4uIRpEq', 'admin');

INSERT INTO roles(name) VALUES ('ROLE_USER');
INSERT INTO roles(name) VALUES ('ROLE_ADMIN');

INSERT INTO user_roles(user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES (2, 2);