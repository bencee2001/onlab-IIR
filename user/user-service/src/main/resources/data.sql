TRUNCATE TABLE users CASCADE;

INSERT INTO users (id, name, username, password,role, schoolid, classid)
VALUES
    (100, 'Eric Cartman','ericcart','1234', 'ROLE_STUDENT',1,1),
    (101, 'Kyle Brovlowsky','kylebro','1234', 'ROLE_STUDENT',1,1),
    (102, 'Kenny McCormick','McKenny','1234', 'ROLE_STUDENT',1,1),
    (103, 'Tolkien Black', 'token', '1234', 'ROLE_STUDENT',1,2),
    (104, 'PC Principal', 'pcp104', '1234', 'ROLE_PRINCIPAL',1,null),
    (105, 'Diane Choksondik', 'dianCHok', '1234', 'ROLE_TEACHER',1,1),
    (112, 'Herbert Gerrison', 'mrPresident', '1234', 'ROLE_TEACHER',1,2),


    (106, 'Bart Simpson','elbarto','1234', 'ROLE_STUDENT',2,1),
    (107, 'Lisa Simpson','lisaS','1234', 'ROLE_STUDENT',2,2),
    (108, 'Milhouse Van Houten','milVH','1234', 'ROLE_STUDENT',2,1),
    (109, 'Nelson Muntz', 'neslon', '1234', 'ROLE_STUDENT',2,1),
    (110, 'Seymour Sinter', 'sinter', '1234', 'ROLE_PRINCIPAL',2,null),
    (111, 'Edna Vadalma', 'dianCHok', '1234', 'ROLE_TEACHER',2,2)






