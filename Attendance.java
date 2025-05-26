package com.user.model;

import java.sql.Date;

public class Attendance {
	    public Attendance(int id, String studentName, Date date, String status) {
			super();
			this.id = id;
			this.studentName = studentName;
			this.date = date;
			this.status = status;
		}
		private int id;
	    private String studentName;
	    private java.sql.Date date;
	    private String status;  // "Present" or "Absent"

	    // Constructors
	    public Attendance() {}

	    public Attendance(String studentName, java.sql.Date date, String status) {
	        this.studentName = studentName;
	        this.date = date;
	        this.status = status;
	    }

	    // Getters and setters
	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getStudentName() { return studentName; }
	    public void setStudentName(String studentName) { this.studentName = studentName; }

	    public java.sql.Date getDate() { return date; }
	    public void setDate(java.sql.Date date) { this.date = date; }

	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }

		@Override
		public String toString() {
			return "Attendance [id=" + id + ", studentName=" + studentName + ", date=" + date + ", status=" + status
					+ "]";
		}
	}



