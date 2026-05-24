// File: TarifStrategy.java
public interface TarifStrategy {
    // Sekarang menerima tarif dasar dari luar
    float hitungTarif(float jarak, float waktu, float baseRateJarak, float baseRateMenit);
}