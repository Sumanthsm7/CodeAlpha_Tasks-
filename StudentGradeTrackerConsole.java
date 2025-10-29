import java.util.*;
import java.io.*;

public class StudentGradeTrackerConsole {
    static class Student {
        String name;
        double grade;
        Student(String name, double grade) { this.name = name; this.grade = grade; }
    }

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== STUDENT GRADE TRACKER ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Average / Highest / Lowest");
            System.out.println("4. Save / Load");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = getInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> showStudents();
                case 3 -> showStats();
                case 4 -> fileMenu();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter grade (0–100): ");
        double g = getDouble();
        students.add(new Student(name, g));
        System.out.println("Added successfully.");
    }

    static void showStudents() {
        if (students.isEmpty()) { System.out.println("No students."); return; }
        System.out.println("\nName\t\tGrade");
        for (Student s : students)
            System.out.printf("%-12s %.2f%n", s.name, s.grade);
    }

    static void showStats() {
        if (students.isEmpty()) { System.out.println("No students."); return; }
        double sum = 0;
        double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        String high = "", low = "";
        for (Student s : students) {
            sum += s.grade;
            if (s.grade > max) { max = s.grade; high = s.name; }
            if (s.grade < min) { min = s.grade; low = s.name; }
        }
        System.out.printf("Average: %.2f | Highest: %s (%.2f) | Lowest: %s (%.2f)%n",
                sum / students.size(), high, max, low, min);
    }

    static void fileMenu() {
        System.out.println("1. Save  2. Load");
        int c = getInt();
        System.out.print("Enter filename: ");
        String f = sc.nextLine();
        if (c == 1) save(f);
        else if (c == 2) load(f);
    }

    static void save(String f) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(f))) {
            for (Student s : students)
                pw.println(s.name + "," + s.grade);
            System.out.println("Saved to " + f);
        } catch (Exception e) { System.out.println("Error saving."); }
    }

    static void load(String f) {
        try (Scanner fs = new Scanner(new File(f))) {
            students.clear();
            while (fs.hasNextLine()) {
                String[] p = fs.nextLine().split(",");
                students.add(new Student(p[0], Double.parseDouble(p[1])));
            }
            System.out.println("Loaded " + students.size() + " students.");
        } catch (Exception e) { System.out.println("Error loading."); }
    }

    static int getInt() {
        while (true) try { return Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { System.out.print("Enter a number: "); }
    }
    static double getDouble() {
        while (true) try { return Double.parseDouble(sc.nextLine()); }
        catch (Exception e) { System.out.print("Enter a valid number: "); }
    }
}
