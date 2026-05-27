public class Driver implements DriverObserver {
    private String driverId;

    public Driver(String driverId) {
        this.driverId = driverId;
    }

    @Override
    public void update(OrderService order) {
        // Jika order masih mencari driver, tampilkan notifikasi masuk
        if (order.getState().getStatusName().equals("SearchingDriver")) {
            System.out.println("[OBSERVER] Driver " + driverId + " menerima notifikasi pesanan masuk.");
        } else {
            // Jika statusnya sudah berubah, berarti sudah diambil driver lain!
            System.out.println("[OBSERVER] Driver " + driverId + ": Pesanan sudah diambil driver lain. Menghapus dari radar.");
        }
    }

    public void acceptOrder(OrderService order) {
        if (order.getState().getStatusName().equals("SearchingDriver")) {
            System.out.println("\n[INFO] Driver " + driverId + " berhasil mengambil pesanan!");
            order.setState(new DriverOnTheWay()); 
            
            // Broadcast lagi ke observer lain bahwa status sudah berubah bukan SearchingDriver lagi
            order.notifyObservers();
        } else {
            System.out.println("[INFO] Driver " + driverId + " gagal mengambil. Pesanan sudah keduluan driver lain.");
        }
    }
}