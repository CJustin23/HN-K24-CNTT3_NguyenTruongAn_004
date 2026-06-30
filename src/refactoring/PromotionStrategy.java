public interface PromotionStrategy {
    /**
     * Apply promotion on base fee. Implementations should be free of side-effects.
     */
    double apply(double baseFee, Student student, Course course);
}

