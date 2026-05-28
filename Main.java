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

        try {
            // Penumpang menekan tombol pesan -> Trigger State SearchingDriver
            order.jalan(); 
            
            Thread.sleep(1500); // Simulasi sistem butuh waktu 1.5 detik mencari driver

            // Driver Budi mendahului mengambil pesanan
            driverB.acceptOrder(order);

            System.out.println("\n=== 3. SIKLUS PERJALANAN & PROSES CEGAH BUG CANCEL ===");
            
            Thread.sleep(2000); // Simulasi driver butuh waktu 2 detik meluncur ke lokasi jemput
            
            // Driver sampai di lokasi jemput, mengubah state menjadi TripStarted
            order.jalan(); 

            // Eksperimen Bug: Penumpang nakal mencoba cancel di tengah jalan
            order.cancelOrder(); 
            
            Thread.sleep(2500); // Simulasi perjalanan ke tujuan memakan waktu 2.5 detik

            // Perjalanan selesai sampai tujuan
            System.out.println();
            order.jalan(); 

        } catch (InterruptedException e) {
            System.out.println("Simulasi terganggu: " + e.getMessage());
        }
    }
}