public class TarifHujanBadai implements TarifStrategy {
    private final float multiplier = 2.5f; 
    @Override
    public float hitungTarif(float jarak, float waktu, float baseRateJarak, float baseRateMenit) {
        System.out.println("[INFO] Menggunakan Strategy: TarifHujanBadai");
        float tarifDasar = (jarak * baseRateJarak) + (waktu * baseRateMenit);
        return tarifDasar * multiplier;
    }
}