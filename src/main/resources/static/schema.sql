INSERT INTO users (username, password, email) VALUES
                                                  ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
                                                  ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

INSERT INTO roles (name) VALUES
                             ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
                                               (1, 1), (2, 2);
