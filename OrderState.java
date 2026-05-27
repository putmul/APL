public interface OrderState {
    void handleAction(OrderService order);
    String getStatusName();
}