TRUNCATE TABLE users CASCADE;

INSERT INTO users (id, name, username, password,role, schoolid, classid)
VALUES (1, 'Eric Cartman','ericcart','1234', 'ROLE_STUDENT',1,1),(2, 'Kyle Brovlowsky','kylebro','1234', 'ROLE_STUDENT',1,1)

