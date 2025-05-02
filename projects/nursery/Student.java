package nursery;
class Student {
        String studentId;
        String studentName;
        int age;
        String guardianName;
        NurseryClass registeredClass;

        public Student(String studentId, String studentName, int age, String guardianName) {
            this.studentId = studentId;
            this.studentName = studentName;
            this.age = age;
            this.guardianName = guardianName;
        }
    }

