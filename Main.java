public class Main {
    public static void main(String[] args) {
        float baseRatePerKm    = 2000.0f;  // Rp 2.000 / km
        float baseRatePerMenit = 500.0f;   // Rp 500 / menit
        float dummyJarak = 5.0f;   // 5 km
        float dummyWaktu = 15.0f;  // 15 menit

        OrderService order = new OrderService(baseRatePerKm, baseRatePerMenit);

        System.out.println("=== 1. SIMULASI STRATEGY PATTERN ===");
        // --- Strategy 1: TarifNormal (default) ---
        System.out.printf("Harga Normal: Rp%.0f%n%n", order.kalkulasiHarga(dummyJarak, dummyWaktu));

        // --- Swap ke Strategy 2: TarifHujanBadai ---
        order.setTarifStrategy(new TarifHujanBadai());
        System.out.printf("Harga Hujan Badai: Rp%.0f%n%n", order.kalkulasiHarga(dummyJarak, dummyWaktu));

        // --- Swap ke Strategy 3: TarifMacetTotal ---
        order.setTarifStrategy(new TarifMacetTotal());
        System.out.printf("Harga Macet Total: Rp%.0f%n%n", order.kalkulasiHarga(dummyJarak, dummyWaktu));

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