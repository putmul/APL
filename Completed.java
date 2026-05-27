public class Completed implements OrderState {
    @Override
    public void handleAction(OrderService order) {
        System.out.println("[STATE] Status: " + getStatusName() + " (Perjalanan selesai, penumpang sampai tujuan)");
    }

    @Override
    public void cancel(OrderService order) {
        System.out.println("[ERROR] Gagal Cancel! Perjalanan sudah selesai dilakukan.");
    }

    @Override
    public String getStatusName() { return "Completed"; }
}