import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PromotionRegistry {
    private final Map<String, PromotionStrategy> registry = new ConcurrentHashMap<>();

    public void register(String code, PromotionStrategy strategy) {
        if (code == null || strategy == null) throw new IllegalArgumentException("code and strategy required");
        registry.put(code, strategy);
    }

    public PromotionStrategy resolve(String code) {
        if (code == null) return new NoPromotion();
        PromotionStrategy s = registry.get(code);
        return s != null ? s : new NoPromotion();
    }
}

