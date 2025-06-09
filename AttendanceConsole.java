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
            System.out.println("\n===== Attendance Management System =====");
            System.out.println("1. Add Attendance");
            System.out.println("2. View All Attendance Records");
            System.out.println("3. Search by Student Name");
            System.out.println("4. Delete Attendance by ID");
            System.out.println("5. Attendance Statistics");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> addAttendance();
                case "2" -> viewAttendance();
                case "3" -> searchByName();
                case "4" -> deleteAttendance();
                case "5" -> showStatistics();
                case "6" -> System.exit(0);
                default -> System.out.println("[Invalid Input] Please choose between 1-6.");
            }
        }
    }

    private static void addAttendance() {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();
            if (!isValidName(name)) {
                System.out.println("[Validation Failed] Name cannot be empty and must contain only letters.");
                return;
            }

            System.out.print("Enter date (yyyy-mm-dd): ");
            Date date = parseDate(scanner.nextLine().trim());
            if (date == null) return;

            System.out.print("Enter status (Present/Absent): ");
            String status = scanner.nextLine().trim();
            if (!status.equalsIgnoreCase("Present") && !status.equalsIgnoreCase("Absent")) {
                System.out.println("[Validation Failed] Status must be either 'Present' or 'Absent'.");
                return;
            }

            Attendance att = new Attendance(name, date, capitalize(status));
            System.out.println(dao.addAttendance(att) ? "[Success] Attendance added." : "[Failed] Could not add record.");
        } catch (Exception e) {
            System.err.println("[Unexpected Error] " + e.getMessage());
        }
    }

    private static void viewAttendance() {
        List<Attendance> list = dao.getAllAttendance();
        if (list.isEmpty()) {
            System.out.println("[Info] No attendance records found.");
            return;
        }
        System.out.printf("%-5s %-20s %-12s %-10s%n", "ID", "Student Name", "Date", "Status");
        System.out.println("------------------------------------------------");
        list.forEach(a -> System.out.printf("%-5d %-20s %-12s %-10s%n",
                a.getId(), a.getStudentName(), a.getDate(), a.getStatus()));
    }

    private static void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine().trim();
        List<Attendance> list = dao.getAttendanceByName(name);
        if (list.isEmpty()) System.out.println("[Info] No matching records found.");
        else list.forEach(System.out::println);
    }

    private static void deleteAttendance() {
        try {
            System.out.print("Enter ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            System.out.println(dao.deleteById(id) ? "[Success] Record deleted." : "[Failed] Could not delete record.");
        } catch (NumberFormatException e) {
            System.out.println("[Validation Failed] ID must be a valid number.");
        }
    }

    private static void showStatistics() {
        List<Attendance> list = dao.getAllAttendance();
        long presentCount = list.stream().filter(a -> a.getStatus().equalsIgnoreCase("Present")).count();
        long absentCount = list.stream().filter(a -> a.getStatus().equalsIgnoreCase("Absent")).count();
        System.out.println("Total Records: " + list.size());
        System.out.println("Present: " + presentCount);
        System.out.println("Absent: " + absentCount);
    }

    private static Date parseDate(String input) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(input);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println("[Validation Failed] Invalid date format. Use yyyy-mm-dd.");
            return null;
        }
    }

    private static String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    private static boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+") && !name.isEmpty();
    }
}

