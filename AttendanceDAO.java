package com.user.dao;

import com.user.model.Attendance;
import java.sql.*;
import java.util.*;

public class AttendanceDAO {
    private final String url = "jdbc:mysql://localhost:3306/attendance_db";
    private final String user = "root";
    private final String password = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public boolean addAttendance(Attendance attendance) {
        String sql = "INSERT INTO attendance(student_name, date, status) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, attendance.getStudentName());
            ps.setDate(2, attendance.getDate());
            ps.setString(3, attendance.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("[Error] Duplicate entry. Attendance already recorded for this student on this date.");
        } catch (SQLException e) {
            System.err.println("[SQL Error] " + e.getMessage());
        }
        return false;
    }

    public List<Attendance> getAllAttendance() {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance ORDER BY date, student_name";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Attendance(
                    rs.getInt("id"),
                    rs.getString("student_name"),
                    rs.getDate("date"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("[Error Fetching Attendance] " + e.getMessage());
        }
        return list;
    }

    public List<Attendance> getAttendanceByName(String name) {
        List<Attendance> list = new ArrayList<>();
        String sql = "SELECT * FROM attendance WHERE student_name LIKE ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Attendance(
                    rs.getInt("id"),
                    rs.getString("student_name"),
                    rs.getDate("date"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("[Error Searching by Name] " + e.getMessage());
        }
        return list;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM attendance WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("[Error Deleting Record] " + e.getMessage());
        }
        return false;
    }
}
