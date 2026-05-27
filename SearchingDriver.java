public class SearchingDriver implements OrderState {
    @Override
    public void handleAction(OrderService order) {
        System.out.println("[STATE] Status: " + getStatusName() + " (Sistem sedang melakukan broadcast ke driver...)");
        order.notifyObservers(); 
    }

    @Override
    public void cancel(OrderService order) {
        System.out.println("[CANCEL] Pesanan berhasil dibatalkan oleh penumpang saat mencari driver.");
        // Anda bisa membuat State Cancelled jika diperlukan, atau langsung selesaikan.
    }

    @Override
    public String getStatusName() { return "SearchingDriver"; }
}