# STUDI KASUS 5: REAL-TIME MATCHMAKING OJEK ONLINE

Repositori ini berisi implementasi simulasi sistem pemesanan transportasi online (ride-hailing) berbasis Java CLI. Proyek ini mendemonstrasikan bagaimana tiga *Design Pattern* utama dapat dikombinasikan secara harmonis untuk menyelesaikan masalah bisnis yang kompleks pada satu kesatuan objek pesanan (`OrderService`).

---

## Masalah & Solusi Arsitektur (Design Patterns)

### 1. Strategy Pattern (Kalkulasi Tarif Dinamis)
* **Masalah:** Perubahan harga tarif akibat kondisi ekstrem (seperti cuaca buruk atau kemacetan total) sering terlambat jika dihitung manual atau menggunakan percabangan `if-else` yang kaku.
* **Solusi:** Rumus perhitungan tarif diisolasi ke dalam strategi terpisah (`TarifNormal`, `TarifHujanBadai`, `TarifMacetTotal`) melalui interface `TarifStrategy`. `OrderService` dapat menukar algoritma ini secara dinamis saat runtime tanpa mengubah kode inti.

### 2. Observer Pattern (Broadcast & Sinkronisasi Radar Driver)
* **Masalah:** Ketika penumpang menekan tombol "Pesan", sistem harus memberi tahu sekelompok driver terdekat secara *real-time*. Jika pesanan sudah diambil oleh satu driver, driver lain harus berhenti melihat pesanan tersebut agar tidak terjadi duplikasi pengambilan.
* **Solusi:** `OrderService` bertindak sebagai *Subject* yang menyimpan daftar `DriverObserver`. Saat pesanan masuk ke status pencarian, sistem melakukan `notifyObservers()`. Begitu pesanan diambil oleh satu driver, status berubah dan sistem kembali melakukan broadcast untuk memberi tahu driver lain bahwa pesanan sudah tidak tersedia (menghapusnya dari radar).

### 3. State Pattern (Kontrol Siklus Hidup & Proteksi Bug Pembatalan)
* **Masalah:** Mengontrol transisi status pesanan yang ketat (`SearchingDriver` → `DriverOnTheWay` → `TripStarted` → `Completed`) dan mencegah bug kritis, seperti penumpang yang mencoba membatalkan pesanan (*cancel*) ketika perjalanan sudah dimulai (`TripStarted`).
* **Solusi:** Setiap status diisolasi menjadi objek mandiri yang mengimplementasikan interface `OrderState`. Logika pengecekan pembatalan didelegasikan ke masing-masing objek state konkrit tersebut, sehingga `OrderService` terbebas dari pengecekan kondisi yang rumit.

---

## Struktur Kode & Komponen Berkas

### A. Komponen Strategy Pattern
* **`TarifStrategy.java`**: Interface wajib yang menyatukan parameter masukan rumus perhitungan tarif (jarak, waktu, base rate).
* **`TarifNormal.java`**: Mengimplementasikan rumus reguler dengan faktor pengali dasar (1.0x).
* **`TarifMacetTotal.java`**: Mengimplementasikan surge pricing kemacetan parah dengan faktor pengali 1.8x.
* **`TarifHujanBadai.java`**: Mengimplementasikan surge pricing cuaca ekstrem dengan faktor pengali 2.5x.

### B. Komponen Observer Pattern
* **`DriverObserver.java`**: Interface bagi objek yang ingin mengamati perubahan data pesanan (`update()`).
* **`Driver.java`**: Kelas konkrit driver yang akan menerima notifikasi masuk atau notifikasi penghapusan radar jika pesanan sudah diambil oleh driver lain.

### C. Komponen State Pattern
* **`OrderState.java`**: Interface yang mendefinisikan aksi transisi (`handleAction`) dan aksi pembatalan (`cancel`).
* **`SearchingDriver.java`**: State awal; mengizinkan pembatalan dan memicu broadcast pesanan ke driver.
* **`DriverOnTheWay.java`**: State saat driver menuju konsumen; mengizinkan pembatalan dengan kondisi tertentu.
* **`TripStarted.java`**: Perjalanan dimulai; **memblokir penuh aksi pembatalan** untuk mencegah bug sistem.
* **`Completed.java`**: Perjalanan selesai; pesanan telah ditutup dengan sukses.

### D. Pusat Kendali & Simulasi
* **`OrderService.java`**: Berperan ganda sebagai *Context* (State), *Subject* (Observer), sekaligus pemilik referensi strategi tarif aktif (Strategy).
* **`Main.java`**: Kelas eksekusi utama yang menyediakan data dummy dan mensimulasikan seluruh skenario jalannya aplikasi dari awal hingga akhir.

---

## Alur Simulasi dan Contoh Log Output (Console)

Berikut adalah gambaran log yang dihasilkan ketika simulasi dijalankan melalui `Main.java`:

```text
=== 1. SIMULASI STRATEGY PATTERN ===
[INFO] Menggunakan Strategy: TarifNormal
Harga Normal: Rp17500

[INFO] Menggunakan Strategy: TarifHujanBadai
Harga Hujan Badai: Rp43750

[INFO] Menggunakan Strategy: TarifMacetTotal
Harga Macet Total: Rp31500

=== 2. SIMULASI OBSERVER & STATE PATTERN ===
[STATE] Status: SearchingDriver (Sistem sedang melakukan broadcast ke driver...)
[OBSERVER] Driver Anto menerima notifikasi pesanan masuk.
[OBSERVER] Driver Budi menerima notifikasi pesanan masuk.
[OBSERVER] Driver Candra menerima notifikasi pesanan masuk.

[INFO] Driver Budi berhasil mengambil pesanan!
[OBSERVER] Driver Anto: Pesanan sudah diambil driver lain. Menghapus dari radar.
[OBSERVER] Driver Budi: Pesanan sudah diambil driver lain. Menghapus dari radar.
[OBSERVER] Driver Candra: Pesanan sudah diambil driver lain. Menghapus dari radar.

=== 3. SIKLUS PERJALANAN & PROSES CEGAH BUG CANCEL ===
[STATE] Status: DriverOnTheWay (Driver telah sampai! Memulai perjalanan...)
[ERROR] Gagal Cancel! Perjalanan sudah dimulai. Anda tidak bisa membatalkan pesanan saat berada di jalan!

[STATE] Status: TripStarted (Penumpang sudah dijemput, perjalanan dimulai)
