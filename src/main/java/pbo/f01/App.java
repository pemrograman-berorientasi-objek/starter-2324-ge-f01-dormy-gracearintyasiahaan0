package pbo.f01;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;
import pbo.f01.model.Dorm;
import pbo.f01.model.Student;

public class App {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("DormStudentsPU");
        em = emf.createEntityManager();
        
        Scanner scanner = new Scanner(System.in);
        String command;
        
        do {
            command = scanner.nextLine();
            String[] parts = command.split("#");
            
            switch (parts[0]) {
                case "display-all":
                    displayAll();
                    break;
                case "student-add":
                    addStudent(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4]);
                    break;
                case "dorm-add":
                    	
                    break;
                case "assign":
                    assignStudent(parts[1], parts[2]);
                    break;
                default:
                    break;
            }
            
        } while (!command.equals("---"));
        
        em.close();
        emf.close();
    }

    private static void displayAll() {
        TypedQuery<Dorm> query = em.createQuery("SELECT d FROM Dorm d ORDER BY d.name", Dorm.class);
        List<Dorm> dorms = query.getResultList();

        for (Dorm dorm : dorms) {
            System.out.println(dorm.getName() + "|" + dorm.getGender() + "|" + dorm.getCapacity() + "|" + dorm.getStudents().size());
            List<Student> students = dorm.getStudents();
            students.sort((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
            for (Student student : students) {
                System.out.println(student.getId() + "|" + student.getName() + "|" + student.getEntranceYear());
            }
        }
    }

    private static void addStudent(String id, String name, int entranceYear, String gender) {
        em.getTransaction().begin();
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEntranceYear(entranceYear);
        student.setGender(gender);
        em.persist(student);
        em.getTransaction().commit();
    }

    private static void addDorm(String name, int capacity, String gender) {
        em.getTransaction().begin();
        Dorm dorm = new Dorm();
        dorm.setName(name);
        dorm.setCapacity(capacity);
        dorm.setGender(gender);
        em.persist(dorm);
        em.getTransaction().commit();
    }

    private static void assignStudent(String studentId, String dormName) {
        em.getTransaction().begin();
        Student student = em.find(Student.class, studentId);
        Dorm dorm = em.find(Dorm.class, dormName);

        if (student != null && dorm != null && dorm.getStudents().size() < dorm.getCapacity()) {
            student.setDorm(dorm);
            em.persist(student);
        }

        em.getTransaction().commit();
    }
}
