public interface PaymentStrategy {
    PaymentResult process(Student student, Course course, double amount);
}

