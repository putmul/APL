public class TripStarted implements OrderState {
    @Override
    public void handleAction(OrderService order) {
        System.out.println("[STATE] Status: " + getStatusName() + " (Penumpang sudah dijemput, perjalanan dimulai)");
    }
    @Override
    public String getStatusName() { return "TripStarted"; }
}