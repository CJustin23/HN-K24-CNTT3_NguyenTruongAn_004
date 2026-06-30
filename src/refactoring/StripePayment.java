public class StripePayment implements PaymentStrategy {
    @Override
    public PaymentResult process(Student student, Course course, double amount) {
        // Simulate card processing
        System.out.println("Processing payment via Stripe for " + student.getName() + ", amount=" + amount);
        return new PaymentResult(true, "Processed by Stripe");
    }
}

