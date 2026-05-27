public interface OrderState {
    void handleAction(OrderService order);
    void cancel(OrderService order); // Tambahan untuk fitur pembatalan ketat
    String getStatusName();
}