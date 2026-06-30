import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaymentRegistry {
    private final Map<String, PaymentStrategy> registry = new ConcurrentHashMap<>();

    public void register(String name, PaymentStrategy strategy) {
        if (name == null || strategy == null) throw new IllegalArgumentException("name and strategy required");
        registry.put(name, strategy);
    }

    public PaymentStrategy resolve(String name) {
        if (name == null) throw new UnsupportedPaymentMethodException("null payment method");
        PaymentStrategy s = registry.get(name);
        if (s == null) throw new UnsupportedPaymentMethodException(name);
        return s;
    }
}

