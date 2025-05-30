package com.user.console;
import com.user.dao.AttendanceDAO;
import com.user.model.Attendance; 
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class AttendanceConsole {
    private static AttendanceDAO dao = new AttendanceDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Attendance Management System ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Attendance");
            System.out.println("2. View All Attendance Records");
            System.out.println("3. Exit");

            System.out.print("Your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addAttendance();
                    break;
                case "2":
                    viewAttendance();
                    break;
                case "3":
                    System.out.println("Exiting... Bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addAttendance() {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter date (yyyy-mm-dd): ");
            String dateStr = scanner.nextLine().trim();

            // Validate date format
            Date sqlDate = parseDate(dateStr);
            if (sqlDate == null) {
                System.out.println("Invalid date format.");
                return;
            }

            System.out.print("Enter status (Present/Absent): ");
            String status = scanner.nextLine().trim();

            if (!status.equalsIgnoreCase("Present") && !status.equalsIgnoreCase("Absent")) {
                System.out.println("Status must be either 'Present' or 'Absent'.");
                return;
            }

            Attendance attendance = new Attendance(name, sqlDate, capitalize(status));
            boolean success = dao.addAttendance(attendance);

            if (success) {
                System.out.println("Attendance recorded successfully!");
            } else {
                System.out.println("Failed to record attendance.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewAttendance() {
        List<Attendance> records = dao.getAllAttendance();

        if (records.isEmpty()) {
            System.out.println("No attendance records found.");
            return;
        }

        System.out.println("\nAttendance Records:");
        System.out.println("ID | Student Name | Date       | Status");
        System.out.println("----------------------------------------");

        for (Attendance a : records) {
            System.out.printf("%d  | %s | %s | %s%n",
                    a.getId(), a.getStudentName(), a.getDate(), a.getStatus());
        }
    }

    // Helper method to parse string date to java.sql.Date
    private static Date parseDate(String dateStr) {
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            return new Date(utilDate.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
