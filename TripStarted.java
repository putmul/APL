public class TripStarted implements OrderState {
    @Override
    public void handleAction(OrderService order) {
        System.out.println("[STATE] Status: " + getStatusName() + " (Penumpang sudah dijemput, perjalanan dimulai)");
        order.setState(new Completed());
    }

    @Override
    public void cancel(OrderService order) {
        // DI SINI BUG TERCEGAH!
        System.out.println("[ERROR] Gagal Cancel! Perjalanan sudah dimulai. Anda tidak bisa membatalkan pesanan saat berada di jalan!");
    }

    @Override
    public String getStatusName() { return "TripStarted"; }
}