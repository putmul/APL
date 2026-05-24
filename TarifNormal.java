public class TarifNormal implements TarifStrategy {
    @Override
    public float hitungTarif(float jarak, float waktu, float baseRateJarak, float baseRateMenit) {
        System.out.println("[INFO] Menggunakan Strategy: TarifNormal");
        return (jarak * baseRateJarak) + (waktu * baseRateMenit);
    }
}