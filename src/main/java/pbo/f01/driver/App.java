package pbo.f01.driver;

import java.util.*;

import pbo.f01.model.Dorm;
import pbo.f01.model.Student;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Dorm> dorms = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            // System.out.print("Input: ");
            String input = scanner.nextLine();
            String[] parts = input.split("#");
            String command = parts[0];

            switch (command) {
                case "dorm-add":
                    addDorm(parts);
                    break;
                case "student-add":
                    addStudent(parts);
                    break;
                case "assign":
                    assignStudent(parts);
                    break;
                case "display-all":
                    displayAll();
                    break;
                case "---":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
        scanner.close();
    }

    private static void addDorm(String[] parts) {
        String name = parts[1];
        int capacity = Integer.parseInt(parts[2]);
        String gender = parts[3];
        Dorm dorm = new Dorm(name, capacity, gender);
        dorm.setStudents(new ArrayList<>()); // Inisialisasi properti students
        dorms.add(dorm);
        // System.out.println("Dorm added successfully!");
    }
    
    private static void addStudent(String[] parts) {
        String id = parts[1];
        String name = parts[2];
        int entranceYear = Integer.parseInt(parts[3]);
        String gender = parts[4];
        Student student = new Student(id, name, entranceYear, gender);
        students.add(student);
        // System.out.println("Student added successfully!");
    }

    private static void assignStudent(String[] parts) {
        String studentId = parts[1];
        String dormName = parts[2];
        Student student = findStudentById(studentId);
        Dorm dorm = findDormByName(dormName);
        if (student == null) {
            // System.out.println("Student not found!");
            return;
        }
        if (dorm == null) {
            // System.out.println("Dorm not found!");
            return;
        }
        if (!student.getGender().equals(dorm.getGender())) {
            // System.out.println("Student gender does not match dorm gender!");
            return;
        }
        if (dorm.getStudents().size() >= dorm.getCapacity()) {
            // System.out.println("Dorm is already full!");
            return;
        }
        student.setDorm(dorm);
        dorm.getStudents().add(student);
        // System.out.println("Student assigned to dorm successfully!");
    }

    private static void displayAll() {
        // Urutkan dorms berdasarkan nama secara alfabetis
        Collections.sort(dorms, Comparator.comparing(Dorm::getName));
        
        for (Dorm dorm : dorms) {
            System.out.println(dorm.getName() + "|" + dorm.getGender() + "|" + dorm.getCapacity() + "|" + dorm.getStudents().size());
            
            Collections.sort(dorm.getStudents(), Comparator.comparing(Student::getName));
        
            for (Student student : dorm.getStudents()) {
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getEntranceYear());
            }
        }
    }
    

    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Dorm findDormByName(String name) {
        for (Dorm dorm : dorms) {
            if (dorm.getName().equals(name)) {
                return dorm;
            }
        }
        return null;
    }
}
