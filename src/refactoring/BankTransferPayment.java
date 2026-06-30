public class BankTransferPayment implements PaymentStrategy {
    @Override
    public PaymentResult process(Student student, Course course, double amount) {
        // Simulate manual bank transfer flow
        System.out.println("Registered bank transfer for " + student.getName() + ", awaiting manual confirmation. Amount=" + amount);
        return new PaymentResult(true, "Pending manual confirmation");
    }
}

