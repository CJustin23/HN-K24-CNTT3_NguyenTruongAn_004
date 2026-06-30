public class UnsupportedPaymentMethodException extends RuntimeException {
    public UnsupportedPaymentMethodException(String method) {
        super("Unsupported payment method: " + method);
    }
}

