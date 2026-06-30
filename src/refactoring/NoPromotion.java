public class NoPromotion implements PromotionStrategy {
    @Override
    public double apply(double baseFee, Student student, Course course) {
        return baseFee;
    }
}

