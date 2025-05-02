package nursery;

import java.util.*;
public class MiddleClass extends NurseryClass {
         public MiddleClass(String classId) {
            super(classId, "Middle Class", 20);
        }

        @Override
        public boolean enrollStudent(Student student, Set<String> existingStudentIds) {
            if (student.age < 3 || student.age > 4) {
                System.out.println("Age not suitable for Middle Class.");
                return false;
            }
            if (students.size() >= maxCapacity) {
                System.out.println("Middle Class is full.");
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
            progressNote = "Language development and storytelling.";
        }

        @Override
        public void conductActivity(String activityName) {
            activities.add(activityName);
            System.out.println("Conducted: " + activityName);
        }

        @Override
        public void generateClassReport() {
            System.out.println("----- Middle Class Report -----");
            System.out.println("Teacher: " + (assignedTeacher != null ? assignedTeacher.teacherName : "None"));
            System.out.println("Students Enrolled: " + students.size());
            System.out.println("Activities: " + activities);
            System.out.println("Progress: " + progressNote);
        }
}
