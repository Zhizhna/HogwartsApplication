SELECT *
FROM student
WHERE age BETWEEN 10 AND 20;

-- Получить всех студентов, но отобразить только список их имен
SELECT name
FROM student s ;

-- Получить всех студентов, у которых в имени присутствует буква «О»
SELECT *
FROM student
WHERE name LIKE '%O%';

--Получить всех студентов, у которых возраст меньше идентификатора
SELECT *
FROM student s
WHERE age < id;

-- Получить всех студентов, упорядоченных по возрасту
SELECT *
FROM student
ORDER BY age;

INSERT INTO student (name, age) VALUES ('Harry Potter', 17);
