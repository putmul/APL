public class DriverOnTheWay implements OrderState {
    @Override
    public void handleAction(OrderService order) {
        System.out.println("[STATE] Status: " + getStatusName() + " (Driver sedang meluncur ke lokasi penjemputan)");
    }
    @Override
    public String getStatusName() { return "DriverOnTheWay"; }
}