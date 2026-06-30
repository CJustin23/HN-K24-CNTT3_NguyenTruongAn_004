public class Enrollment {
    private final Student student;
    private final Course course;
    private final double fee;

    public Enrollment(Student student, Course course, double fee) {
        this.student = student;
        this.course = course;
        this.fee = fee;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public double getFee() {
        return fee;
    }
}

