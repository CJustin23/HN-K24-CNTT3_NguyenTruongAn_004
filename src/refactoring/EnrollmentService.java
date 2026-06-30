import java.util.Objects;

public class EnrollmentService {
    private final PromotionRegistry promotionRegistry;
    private final PaymentRegistry paymentRegistry;
    private final NotificationService notificationService;

    // Default constructor sets up sensible defaults so existing code can still use new service
    public EnrollmentService() {
        this.promotionRegistry = new PromotionRegistry();
        this.promotionRegistry.register("EARLYBIRD", new EarlyBirdPromotion());
        this.promotionRegistry.register("ALUMNI", new AlumniPromotion());

        this.paymentRegistry = new PaymentRegistry();
        this.paymentRegistry.register("STRIPE", new StripePayment());
        this.paymentRegistry.register("BANK_TRANSFER", new BankTransferPayment());

        this.notificationService = new EmailNotificationService();
    }

    // Constructor for dependency injection / testing
    public EnrollmentService(PromotionRegistry promotionRegistry,
                             PaymentRegistry paymentRegistry,
                             NotificationService notificationService) {
        this.promotionRegistry = Objects.requireNonNull(promotionRegistry);
        this.paymentRegistry = Objects.requireNonNull(paymentRegistry);
        this.notificationService = Objects.requireNonNull(notificationService);
    }

    public Enrollment enroll(Student student, Course course, String coupon, String paymentType) {
        Objects.requireNonNull(student, "student");
        Objects.requireNonNull(course, "course");

        // 1) Calculate fee using promotion strategy
        double baseFee = course.getPrice();
        PromotionStrategy promotion = promotionRegistry.resolve(coupon);
        double fee = promotion.apply(baseFee, student, course);

        // 2) Process payment using payment strategy
        PaymentStrategy payment = paymentRegistry.resolve(paymentType);
        PaymentResult paymentResult = payment.process(student, course, fee);
        if (!paymentResult.isSuccess()) {
            throw new RuntimeException("Payment failed: " + paymentResult.getMessage());
        }

        // 3) Grant access and send notifications
        student.addAccess(course.getId());
        notificationService.notifyEnrollment(student, course, fee);

        // 4) Return enrollment record (persistence can be added separately)
        return new Enrollment(student, course, fee);
    }
}

