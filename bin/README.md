implementasi Strategy Pattern untuk mengatasi masalah keterlambatan perubahan harga saat kondisi ekstrem.
Alur Simulasi dan Contoh Log Output (Console)

TarifStrategy.java 
Fungsi: Berperan untuk menegaskan bahwa strategi tarif apa pun yang dibuat wajib memiliki metode perhitungan harga dengan parameter masukan yang seragam.  

TarifNormal.java 
Fungsi: Mengimplementasikan rumus perhitungan tarif reguler atau dasar dengan faktor pengali 1.0x.  

TarifHujanBadai.java 
Fungsi: Mengimplementasikan logika surge pricing ketika terjadi cuaca ekstrem dengan faktor pengali 2.5x.

TarifMacetTotal.java 
Fungsi: Mengimplementasikan logika surge pricing ketika terjadi kemacetan parah di jalan raya dengan faktor pengali 1.8x.  

**OrderService.java**
Fungsi: Menjadi pusat kendali seluruh operasi bisnis pesanan, memegang referensi strategi tarif yang sedang aktif dan mengeksekusi perhitungan harga. Melalui pemisahan ini, OrderService tidak perlu tahu detail rumus rumit di dalam masing-masing skema tarif, melainkan cukup memanggil fungsi dari interface strategi. 

**Main.java**
Fungsi: Berperan sebagai simulasi aplikasi, penyedia data dummy dan mendemonstrasikan bagaimana sistem dapat mengubah algoritma tarif secara dinamis saat aplikasi berjalan (runtime).
