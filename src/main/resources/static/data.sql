CREATE TABLE employee (
                          id BIGSERIAL PRIMARY KEY,
                          first_name VARCHAR(255),
                          last_name VARCHAR(255),
                          job_title VARCHAR(255),
                          email VARCHAR(255),
                          age INT
);

CREATE TABLE project (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255),
                         description VARCHAR(255),
                         status VARCHAR(255)
);

CREATE TABLE employee_project (
                                  employee_id BIGINT,
                                  project_id BIGINT,
                                  PRIMARY KEY (employee_id, project_id),
                                  FOREIGN KEY (employee_id) REFERENCES employee (id),
                                  FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE
);

CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL
);

CREATE TABLE users_roles (
                             user_id BIGINT NOT NULL,
                             role_id INT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id) REFERENCES users (id),
                             FOREIGN KEY (role_id) REFERENCES roles (id)
);
