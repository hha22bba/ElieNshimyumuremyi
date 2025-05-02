package nursery;

import java.util.*;
public class BabyClass extends NurseryClass{
        public BabyClass(String classId) {
            super(classId, "Baby Class", 15);
        }

        @Override
        public boolean enrollStudent(Student student, Set<String> existingStudentIds) {
            if (student.age < 2 || student.age > 3) {
                System.out.println("Age not suitable for Baby Class.");
                return false;
            }
            if (students.size() >= maxCapacity) {
                System.out.println("Baby Class is full.");
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
            if (!teacher.teacherRole.equals("Early Childhood Educator")) {
                System.out.println("Teacher not qualified for Baby Class.");
                return false;
            }
            this.assignedTeacher = teacher;
            teacher.assignedClass = this;
            return true;
        }

        @Override
        public void trackProgress() {
            progressNote = "Focused on motor skills and play-based learning.";
        }

        @Override
        public void conductActivity(String activityName) {
            activities.add(activityName);
            System.out.println("Conducted: " + activityName);
        }

        @Override
        public void generateClassReport() {
            System.out.println("----- Baby Class Report -----");
            System.out.println("Teacher: " + (assignedTeacher != null ? assignedTeacher.teacherName : "None"));
            System.out.println("Students Enrolled: " + students.size());
            System.out.println("Activities: " + activities);
            System.out.println("Progress: " + progressNote);
        }
    }

