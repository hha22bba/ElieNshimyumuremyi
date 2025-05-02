package nursery;
import java.util.*;
public class TopClass extends NurseryClass{

        public TopClass(String classId) {
            super(classId, "Top Class", 25);
        }

        @Override
        public boolean enrollStudent(Student student, Set<String> existingStudentIds) {
            if (student.age < 4 || student.age > 5) {
                System.out.println("Age not suitable for Top Class.");
                return false;
            }
            if (students.size() >= maxCapacity) {
                System.out.println("Top Class is full.");
                return false;
            }
            if (existingStudentIds.contains(student.studentId)) {
                System.out.println("Duplicate student ID.");
                return false;
            }
            student.registeredClass = this;
            students.add(student);
            existingStudentIds.add(student.studentId);
            return true;
        }

        @Override
        public boolean assignTeacher(Teacher teacher) {
            this.assignedTeacher = teacher;
            teacher.assignedClass = this;
            return true;
        }

        @Override
        public void trackProgress() {
            progressNote = "Basic reading, writing, and arithmetic with term assessments.";
        }

        @Override
        public void conductActivity(String activityName) {
            activities.add(activityName);
            System.out.println("Conducted: " + activityName);
        }

        @Override
        public void generateClassReport() {
            System.out.println("----- Top Class Report -----");
            System.out.println("Teacher: " + (assignedTeacher != null ? assignedTeacher.teacherName : "None"));
            System.out.println("Students Enrolled: " + students.size());
            System.out.println("Activities: " + activities);
            System.out.println("Progress: " + progressNote);
        }
    }

