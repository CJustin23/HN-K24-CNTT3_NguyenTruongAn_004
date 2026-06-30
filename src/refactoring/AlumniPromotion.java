public class AlumniPromotion implements PromotionStrategy {
    private final double discount = 500000.0;

    @Override
    public double apply(double baseFee, Student student, Course course) {
        double result = baseFee - discount;
        return result < 0 ? 0 : result;
    }
}

