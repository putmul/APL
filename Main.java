public class Main {
    private static OrderService setupOrderService() {
        float dummyJarak = 2000.0f;
        float dummyWaktu = 500.0f;
        return new OrderService(dummyJarak, dummyWaktu);
    }

    public static void main(String[] args) {
        float dummyJarak = 5.0f;    
        float dummyWaktu = 15.0f;   

        OrderService order = setupOrderService();
        
        System.out.println("=== 1. SIMULASI STRATEGY PATTERN ===");
        order.setTarifStrategy(new TarifHujanBadai());
        System.out.println("Harga Final: Rp" + order.kalkulasiHarga(dummyJarak, dummyWaktu) + "\n");

        System.out.println("=== 2. SIMULASI OBSERVER & STATE PATTERN ===");
        // Membuat sekumpulan objek Driver (Observers)
        Driver driverA = new Driver("Anto");
        Driver driverB = new Driver("Budi");
        Driver driverC = new Driver("Candra");

        order.registerObserver(driverA);
        order.registerObserver(driverB);
        order.registerObserver(driverC);

        // Penumpang menekan tombol pesan -> Trigger State SearchingDriver
        order.jalan(); 

        // Driver Budi mendahului mengambil pesanan
        driverB.acceptOrder(order);

        System.out.println("\n=== 3. SIKLUS PERJALANAN & PROSES CEGAH BUG CANCEL ===");
        // Driver sampai di lokasi jemput, mengubah state menjadi TripStarted
        order.jalan(); 

        // Eksperimen Bug: Penumpang nakal mencoba cancel di tengah jalan (TripStarted)
        order.cancelOrder(); 

        // Perjalanan selesai sampai tujuan
        System.out.println();
        order.jalan(); 
    }
}