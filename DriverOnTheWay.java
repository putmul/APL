public class DriverOnTheWay implements OrderState {
    @Override
    public void handleAction(OrderService order) {
        System.out.println("[STATE] Status: " + getStatusName() + " (Driver telah sampai! Memulai perjalanan...)");
        order.setState(new TripStarted());
    }

    @Override
    public void cancel(OrderService order) {
        System.out.println("[CANCEL] Pesanan dibatalkan. Driver dialihkan kembali.");
    }

    @Override
    public String getStatusName() { return "DriverOnTheWay"; }
}