public class OrderService {
    private TarifStrategy tarifStrategy;
    private float baseRateJarak;
    private float baseRateMenit;

    // Konstruktor dengan base rate
    public OrderService(float baseRateJarak, float baseRateMenit) {
        this.baseRateJarak = baseRateJarak;
        this.baseRateMenit = baseRateMenit;
        this.tarifStrategy = new TarifNormal(); // Default
    }

    public void setTarifStrategy(TarifStrategy tarifStrategy) {
        this.tarifStrategy = tarifStrategy;
    }

    // Mengeksekusi kalkulasi berdasarkan strategy yang sedang aktif
    public float kalkulasiHarga(float jarak, float waktu) {
        if (this.tarifStrategy == null) {
            throw new IllegalStateException("Strategi tarif belum diatur!");
        }
        return this.tarifStrategy.hitungTarif(jarak, waktu, this.baseRateJarak, this.baseRateMenit);
    }
}