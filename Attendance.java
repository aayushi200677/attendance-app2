package com.user.model;

import java.sql.Date;

public class Attendance {
    private int id;
    private String studentName;
    private Date date;
    private String status;

    public Attendance() {}

    public Attendance(int id, String studentName, Date date, String status) {
        this.id = id;
        this.studentName = studentName;
        this.date = date;
        this.status = status;
    }

    public Attendance(String studentName, Date date, String status) {
        this.studentName = studentName;
        this.date = date;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + " | " + studentName + " | " + date + " | " + status;
    }
}






