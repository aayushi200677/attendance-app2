# 📘 Attendance Management System (Console-Based)

A simple and efficient *Attendance Management System* built using *Java, **MySQL, and **JDBC*. This console-based application enables administrators or teachers to manage student attendance records with ease.

---

## 🚀 Features

- ✅ Add new attendance records  
- ✅ View all student attendance  
- ✅ Search attendance by student name  
- ✅ Delete attendance records by ID  
- 🔐 MySQL database integration using JDBC  
- 💻 User-friendly CLI interface (no GUI or servlet)

---

## 🛠 Requirements

Before you begin, ensure you have the following tools installed:

- ✅ Java Development Kit (JDK 8 or above)  
- ✅ MySQL Server (via XAMPP, WAMP, or standalone)  
- ✅ MySQL JDBC Driver (Connector/J)  
- ✅ Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or terminal-based setup  

---

## 💾 Database Setup

Follow these steps to set up the database:

1. Open your MySQL client (phpMyAdmin, MySQL CLI, etc.)
2. Run the following command to create the database:

sql
CREATE DATABASE attendance_db;


3. Create the attendance table:

sql
USE attendance_db;

CREATE TABLE attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    status VARCHAR(10) NOT NULL,
    UNIQUE(student_name, date)
);


---

## 📂 Project Structure


com/
└── user/
    ├── model/
    │   └── Attendance.java
    ├── dao/
    │   └── AttendanceDAO.java
    └── console/
        └── AttendanceConsole.java


---

## 🧪 How to Run

1. Clone the repository or download the code.
2. Set up the database as mentioned above.
3. Make sure the JDBC driver (Connector/J) is in your project’s classpath.
4. Compile and run the AttendanceConsole.java file.
5. Interact with the application via terminal.

---

## 🧩 Future Improvements

- ✅ Update attendance functionality  
- 📈 Monthly or weekly attendance reports  
- 📤 Export attendance to PDF/Excel  
- 🔐 User authentication (admin/teacher login)

---

## 📜 License

This project is for educational purposes. Feel free to modify and use it!

---
### ✅ To Update It on GitHub:
1. Save the above content in a local file called README.md.
2. Move it to the root directory of your project.
3. Then run:
```bash
git add README.md
git commit -m "Updated README with project overview and setup instructions"
git push origin main
