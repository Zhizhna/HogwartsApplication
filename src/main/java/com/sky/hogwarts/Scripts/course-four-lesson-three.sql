CREATE TABLE student (
    id BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    age INT DEFAULT 20 CHECK (age >= 16)
);

CREATE TABLE faculty (
    id BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    color TEXT NOT NULL
);


CREATE INDEX idx_student_name ON student(name);


CREATE INDEX idx_faculty_name_color ON faculty(name, color);