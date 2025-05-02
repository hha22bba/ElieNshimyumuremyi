package nursery;

import java.util.*;
abstract class NurseryClass {

        String classId;
        String className;
        int maxCapacity;
        Teacher assignedTeacher;
        List<Student> students = new ArrayList<>();
        List<String> activities = new ArrayList<>();
        String progressNote = "";

        public NurseryClass(String classId, String className, int maxCapacity) {
            this.classId = classId;
            this.className = className;
            this.maxCapacity = maxCapacity;
        }

        public abstract boolean enrollStudent(Student student, Set<String> existingStudentIds);
        public abstract void trackProgress();
        public abstract void conductActivity(String activityName);
        public abstract void generateClassReport();
        public abstract boolean assignTeacher(Teacher teacher);
    }

