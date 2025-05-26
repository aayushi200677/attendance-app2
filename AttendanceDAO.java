package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.model.Attendance; // Assuming you have this model class

public class AttendanceDAO {

    private final String url = "jdbc:mysql://localhost:3306/attendance_db";
    private final String user = "root"; // Change as per your MySQL setup
    private final String password = "password"; // Change as per your MySQL setup

    // Establish connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Add attendance record
    public boolean addAttendance(Attendance attendance) {
        String sql = "INSERT INTO attendance(student_name, date, status) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, attendance.getStudentName());
            ps.setDate(2, attendance.getDate());
            ps.setString(3, attendance.getStatus());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Attendance for this student on this date already recorded.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all attendance records
    public List<Attendance> getAllAttendance() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance ORDER BY date, student_name";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Attendance att = new Attendance();
                att.setId(rs.getInt("id"));
                att.setStudentName(rs.getString("student_name"));
                att.setDate(rs.getDate("date"));
                att.setStatus(rs.getString("status"));
                list.add(att);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

