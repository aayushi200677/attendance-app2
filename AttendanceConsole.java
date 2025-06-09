package com.user.console;

import com.user.dao.AttendanceDAO;
import com.user.model.Attendance;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AttendanceConsole {
    private static final AttendanceDAO dao = new AttendanceDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Attendance Management System ===");
            System.out.println("1. Add Attendance");
            System.out.println("2. View All Attendance Records");
            System.out.println("3. Search by Student Name");
            System.out.println("4. Delete Attendance by ID");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> addAttendance();
                case "2" -> viewAttendance();
                case "3" -> searchByName();
                case "4" -> deleteAttendance();
                case "5" -> System.exit(0);
                default -> System.out.println("Invalid input.");
            }
        }
    }

    private static void addAttendance() {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty."); return;
            }

            System.out.print("Enter date (yyyy-mm-dd): ");
            Date date = parseDate(scanner.nextLine().trim());
            if (date == null) return;

            System.out.print("Enter status (Present/Absent): ");
            String status = scanner.nextLine().trim();
            if (!status.equalsIgnoreCase("Present") && !status.equalsIgnoreCase("Absent")) {
                System.out.println("Invalid status."); return;
            }

            Attendance att = new Attendance(name, date, capitalize(status));
            System.out.println(dao.addAttendance(att) ? "Attendance added." : "Failed to add.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAttendance() {
        List<Attendance> list = dao.getAllAttendance();
        if (list.isEmpty()) {
            System.out.println("No records found."); return;
        }
        System.out.println("ID | Student Name | Date | Status");
        System.out.println("---------------------------------");
        for (Attendance a : list) System.out.println(a);
    }

    private static void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        List<Attendance> list = dao.getAttendanceByName(name);
        if (list.isEmpty()) System.out.println("No records found.");
        else list.forEach(System.out::println);
    }

    private static void deleteAttendance() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(dao.deleteById(id) ? "Deleted successfully." : "Delete failed.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
        }
    }

    private static Date parseDate(String input) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(input);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
            return null;
        }
    }

    private static String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
