import java.util.ArrayList;
import java.util.List;

public class OrderService {
    // 1. Kamus untuk Strategy Pattern
    private TarifStrategy tarifStrategy;
    private float baseRateJarak;
    private float baseRateMenit;

    // 2. Kamus untuk State Pattern
    private OrderState currentState;

    // 3. Kamus untuk Observer Pattern
    private List<DriverObserver> observers = new ArrayList<>();

    public OrderService(float baseRateJarak, float baseRateMenit) {
        this.baseRateJarak = baseRateJarak;
        this.baseRateMenit = baseRateMenit;
        this.tarifStrategy = new TarifNormal(); // Default Strategy
        this.currentState = new SearchingDriver(); // Default State awal
    }

    // === STRATEGY PATTERN METHODS ===
    public void setTarifStrategy(TarifStrategy tarifStrategy) {
        this.tarifStrategy = tarifStrategy;
    }

    public float kalkulasiHarga(float jarak, float waktu) {
        if (this.tarifStrategy == null) {
            throw new IllegalStateException("Strategi tarif belum diatur!");
        }
        return this.tarifStrategy.hitungTarif(jarak, waktu, this.baseRateJarak, this.baseRateMenit);
    }

    // === STATE PATTERN METHODS ===
    public void setState(OrderState state) {
        this.currentState = state;
    }

    public OrderState getState() {
        return this.currentState;
    }

    // Menjalankan aksi transisi maju berikutnya
    public void jalan() {
        this.currentState.handleAction(this);
    }

    // Aksi ketika penumpang menekan tombol cancel
    public void cancelOrder() {
        this.currentState.cancel(this);
    }

    // === OBSERVER PATTERN METHODS ===
    public void registerObserver(DriverObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DriverObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        // Melakukan clone list untuk menghindari ConcurrentModificationException jika list diubah saat looping
        List<DriverObserver> targets = new ArrayList<>(observers);
        for (DriverObserver observer : targets) {
            observer.update(this);
        }
    }
}