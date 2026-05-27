public class Driver implements DriverObserver {
    private String driverId;

    public Driver(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public void update(OrderService order) {
        System.out.println("[OBSERVER] Driver " + driverId + " menerima notifikasi pesanan masuk.");
    }

    // Simulasi ketika driver menekan tombol "Terima"
    public void acceptOrder(OrderService order) {
        System.out.println("\n[INFO] Driver " + driverId + " berhasil mengambil pesanan!");
        // Transisi status ke DriverOnTheWay
        order.setState(new DriverOnTheWay()); 
    }
}