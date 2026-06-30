public class EmailNotificationService implements NotificationService {
    @Override
    public void notifyEnrollment(Student student, Course course, double finalFee) {
        // In real system we'd enqueue/send an email; here we simulate
        System.out.println("Sending welcome email to " + student.getName() + " for course " + course.getId() + ", fee=" + finalFee);
    }
}

