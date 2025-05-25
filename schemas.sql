CREATE DATABASE attendance_db;

USE attendance_db;

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    date DATE,
    status ENUM('Present', 'Absent'),
    UNIQUE(student_name, date) -- to avoid duplicate attendance for same day
);
