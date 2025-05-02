package nursery;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class MainMenu {

        static Scanner scanner = new Scanner(System.in);
        static Set<String> studentIds = new HashSet<>();

        static BabyClass babyClass = new BabyClass("C001");
        static MiddleClass middleClass = new MiddleClass("C002");
        static TopClass topClass = new TopClass("C003");

        static List<Teacher> teachers = new ArrayList<>();
        static Map<String, NurseryClass> classMap = Map.of(
                "Baby", babyClass,
                "Middle", middleClass,
                "Top", topClass
        );

        public static void main(String[] args) {
            boolean running = true;
            while (running) {
                System.out.println("\n====== Nursery School Management ======");
                System.out.println("1. Assign Teacher");
                System.out.println("2. Enroll Student");
                System.out.println("3. Conduct Activity");
                System.out.println("4. Track Progress");
                System.out.println("5. Generate & Save Class Report");
                System.out.println("6. Exit");
                System.out.print("Choose option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> assignTeacher();
                    case 2 -> enrollStudent();
                    case 3 -> conductActivity();
                    case 4 -> trackClassProgress();
                    case 5 -> generateAndSaveReport();
                    case 6 -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            }
        }

        static void assignTeacher() {
            System.out.print("Enter Teacher ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Role: ");
            String role = scanner.nextLine();

            Teacher t = new Teacher(id, name, role);
            teachers.add(t);

            System.out.print("Assign to (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();
            NurseryClass cls = classMap.get(classChoice);

            if (cls != null) {
                if (cls.assignTeacher(t)) {
                    System.out.println("Teacher assigned successfully.");
                }
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void enrollStudent() {
            System.out.print("Student ID: ");
            String id = scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Guardian Name: ");
            String guardian = scanner.nextLine();

            Student s = new Student(id, name, age, guardian);
            System.out.print("Enroll in (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                if (cls.enrollStudent(s, studentIds)) {
                    System.out.println("Student enrolled successfully.");
                }
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void conductActivity() {
            System.out.print("Which class (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();
            System.out.print("Activity name: ");
            String activity = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                cls.conductActivity(activity);
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void trackClassProgress() {
            System.out.print("Which class to track (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                cls.trackProgress();
                System.out.println("Progress tracked.");
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void generateAndSaveReport() {
            System.out.print("Generate report for (Baby/Middle/Top): ");
            String classChoice = scanner.nextLine();

            NurseryClass cls = classMap.get(classChoice);
            if (cls != null) {
                cls.generateClassReport();
                saveReportToFile(cls);
            } else {
                System.out.println("Invalid class.");
            }
        }

        static void saveReportToFile(NurseryClass cls) {
            String fileName = cls.className.replace(" ", "_") + "_Report.txt";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write("Class Report: " + cls.className + "\n");
                writer.write("Teacher: " + (cls.assignedTeacher != null ? cls.assignedTeacher.teacherName : "None") + "\n");
                writer.write("Students Enrolled: " + cls.students.size() + "\n");
                writer.write("Activities: " + cls.activities + "\n");
                writer.write("Progress: " + cls.progressNote + "\n");
                System.out.println("Report saved to " + fileName);
            } catch (IOException e) {
                System.out.println("Failed to save report: " + e.getMessage());
            }
        }
    }


