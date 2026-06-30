public class EarlyBirdPromotion implements PromotionStrategy {
    @Override
    public double apply(double baseFee, Student student, Course course) {
        return baseFee * 0.7; // 30% off
    }
}

