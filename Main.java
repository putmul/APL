// File: Main.java
public class Main {
    //data dummy baserate untuk jarak dan waktu 
    private static OrderService setupOrderService() {
        float dummyJarak = 2000.0f;
        float dummyWaktu = 500.0f;
        
        OrderService service = new OrderService(dummyJarak, dummyWaktu);
        return service;
    }
    public static void main(String[] args) {
        float dummyJarak = 5.0f;    // 5 km
        float dummyWaktu = 15.0f;   // 15 menit

        OrderService order = setupOrderService();
        System.out.println("--- Simulasi Kalkulasi Harga ---");
        
        // Cek harga normal
        System.out.println("Harga Normal: Rp" + order.kalkulasiHarga(dummyJarak, dummyWaktu) + "\n");

        // Cek harga saat hujan badai
        order.setTarifStrategy(new TarifHujanBadai());
        System.out.println("Harga Hujan Badai: Rp" + order.kalkulasiHarga(dummyJarak, dummyWaktu) + "\n");

        order.setTarifStrategy(new TarifMacetTotal());
        System.out.println("Harga Macet Total: Rp" + order.kalkulasiHarga(dummyJarak, dummyWaktu) + "\n");
    }
}