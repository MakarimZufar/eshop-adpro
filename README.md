# Pemerograman Lanjut

<h2>
Nama: Makarim Zufar Prambudyo
<br>
NPM : 2306241751
<br>
Kelas: ADPRO B 
</h2>

<details>
<summary>
modul 1 Reflection 1
</summary>
Dalam tutorial yang saya kerjakan ini, saya telah menerapkan Clean Code Principles untuk menjaga kode tetap rapi, mudah dibaca, dan mudah di pelihara. selain itu, saya juga memperhatikan Secure Coding Practices untuk meningkatkan kemanan aplikasi kita dan menghindari potensi eksploitasi. Berikut adalah rincian lengkapnya:

1. Clean Code Principles
-   setiap class, method, dan variabel menggunakan nama yang deskriptif agar langsung bisa dipahami tanpa perlu banyak komentar
-   Single Responsibility Principle
Setiap metode hanya memiliki satu tanggun jawab dan tidak mencampur berbagai tugas sekaligus.
-   DRY (Don't repeat yourself)
kita menggunakan kode findAll() untuk mengambil semua produk, menghindari penulisan kode yang sama berulang kali.
-   Simplicity
kode dibuat sesederhana mungkin tanpa logika yang kompleks dan berbelit-belit
2. Secure Coding Practices
-   menggunakan UUID untuk ID Produk
ID produk dihasilkan secara acak dan unik menggunakan UUID untuk mencegah prediksi id oleh penyerang
-   Validasi Data dan Null Check untuk mencegah error
saat melakukan update atau delete produk, kita memastikan produk benar-benar ada sebelum memodifikasinya.
</details>
<details>
<summary>
modul 1 reflection 2
</summary>

1. setelah menulis unit test, saya merasa lebih yakin terhadap kode yang akhirnya saya deploy atau saya kerjakan karena unit test sendiri berfungsi sebagai test untuk memastikan bahwa semua kode bekeja secara normal. unit test sangat berguna untuk menangani sistem dengan skala yang lebih besar. proses ini mendorong penulisan kode yang lebih modular dan mudah di uji

2. berapa banyak unit test yang harus saya tulis dalam 1 kelas tergantung pada kompleksitas dan jumlah fitur yang di uji contohnya seperti path yang berjalan sesuai harapan, edge case dari logika dan sistem sistem yang ada dan error handling 

3. salah satu cara untuk mengukur kecukupan unit test adalah dengan menggunakan code coverage. code coverage mengukur persentase dari kode yang sudah di cek. namun memiliki 100% coverage belum tentu kode bebas dari bug

4. ketika membuat functional test yang mirip dengan createProductFuntionalTest.java ada beberapa masalah yaitu seperti duplikasi kode yang membuat pemeliharaan lebih sulit, kurangnya sifat reusability. saran mungkin kita bisa menggunakan superclass yang berisi setup umum dan instance variable yang bisa digunakan untuk semua test suite 
</details>

<details>
<summary>
modul 2 reflection 1
</summary>
isu kualitas kode yang diperbaiki dan strategi perbaikannya

1. masalah cangkupan branch dalam metode updateProduct
masalah: beberapa kondisi tidak diuji dalam test case, sehngga ada cabang kode yang tidak tercakup di laporan JaCoCo.
solusi: menambahkan test case untuk skenario id atau updateProduct bernilai null, memastikan semua jalur eksekusi diuji
2. penanganan input null dengan lebih baik
masalah: metode updateProduct tidak menangani kasus di mana updateProduct atau id bernilai null, yang dapat menyebabkan NullPointerException saat runtime
solusi: menambahkan validasi dan test case untuk memastihkan metode menangani null dengan melemparkan exception yang sesuai
3. memastikan update di repository berjalan dengan benar
masalah: test sebelumnya tidak memastikan apakah perubahan data (productName, productQuantity) benar-benar tersimpan di repository
solusi: Memperluas test case untuk memverifikasi bahwa data dalam repository berubah setelah updateProduct dipanngil

analisis CI/CD: apakah sudah memenuhi definisi continous integration dan continous deployment
1. continous integration (CI)
- sudah memenuhi pengujian otomatis, pipeline menjalankan unit test jUniit dan JaCoCo setiap perubahan code
- terintegrasi dengan version Control yaitu github
- penerapan standar kode yang ketat untuk memastikan kualitas code terjaga
2. Continous deployment (CD)
- otomatisasi Deployment sudah terpenuhi ketika kita melakukan push code yang sudah final ke main, akan langsung melakukan deployement ke koyeb
</details>

<details>
<summary>
modul 3 reflection 1
</summary>
  
1. Prinsip yang Diterapkan dalam Proyek
   Dalam proyek ini, kami menerapkan prinsip SOLID untuk memastikan bahwa kode lebih modular, mudah dipelihara, dan scalable. Berikut adalah prinsip yang kami gunakan:
- S (Single Responsibility Principle - SRP):
Setiap kelas hanya memiliki satu tanggung jawab. Misalnya, `CarService` hanya menangani logika bisnis, sedangkan `CarRepository` hanya mengelola penyimpanan data.

- O (Open/Closed Principle - OCP):
Kode terbuka untuk ekstensi tetapi tertutup untuk modifikasi. Kami menggunakan interface `CarRepository`, sehingga kalau nanti ingin mengganti penyimpanan dari in-memory ke database, kami tidak perlu mengubah kode `CarService`.

- L (Liskov Substitution Principle - LSP):
Kami memastikan bahwa subclass bisa menggantikan superclass tanpa menyebabkan error. Misalnya, jika ada ElectricCar sebagai subclass dari `Car`, maka ElectricCar tetap bisa digunakan di mana saja `Car` digunakan.

- I (Interface Segregation Principle - ISP):
Interface dibuat spesifik dan tidak terlalu besar.
Misalnya, kami tidak membuat satu interface besar untuk semua jenis kendaraan, tetapi membaginya menjadi beberapa interface kecil seperti `VehicleService` dan `CarService`.

- D (Dependency Inversion Principle - DIP):
Kelas tingkat tinggi tidak bergantung langsung pada implementasi tingkat rendah. Kami menggunakan dependency injection agar `CarService` hanya bergantung pada interface `CarRepository`, bukan implementasi spesifik seperti `CarRepositoryInMemory`.

2. Keuntungan Menerapkan Prinsip SOLID dalam Proyek Menerapkan SOLID membawa beberapa keuntungan, antara lain:

- ✅ Kode lebih mudah dipelihara
Dengan SRP, jika ada perubahan pada penyimpanan data, cukup ubah di CarRepository, tanpa mengubah CarService.

- ✅ Mudah dikembangkan tanpa mengubah kode lama
Dengan OCP, jika ingin menambahkan CarRepositoryDB (untuk database), kita tidak perlu mengedit kode CarService.

- ✅ Menghindari error ketika mengganti atau menambah fitur
Dengan LSP, CarService bisa menggunakan subclass seperti ElectricCar tanpa risiko error.

- ✅ Lebih fleksibel dan modular
Dengan ISP, interface lebih kecil dan spesifik, sehingga lebih mudah digunakan tanpa membuat kode yang tidak perlu.

- ✅ Lebih mudah diuji (unit testing)
Dengan DIP, kita bisa menggunakan mock repository untuk testing tanpa bergantung pada implementasi konkret.

3. Kerugian Jika Tidak Menerapkan Prinsip SOLID
Jika tidak menerapkan SOLID, proyek bisa mengalami beberapa masalah serius, seperti:

- ❌ Kode sulit dipelihara
Jika CarService menangani penyimpanan data langsung, maka setiap perubahan pada cara penyimpanan harus mengubah kode di banyak tempat.

- ❌ Sulit menambah fitur tanpa merusak kode lama
Jika tidak menggunakan OCP, menambahkan CarRepositoryDB bisa menyebabkan perubahan besar di CarService.

- ❌ Error ketika subclass menggantikan superclass
Jika melanggar LSP, subclass bisa memiliki perilaku berbeda yang tidak kompatibel dengan superclass.

- ❌ Interface terlalu besar dan membebani class yang tidak perlu
Jika melanggar ISP, setiap class akan terpaksa mengimplementasikan method yang tidak relevan dengan tugasnya.

- ❌ Ketergantungan tinggi antar komponen, menyulitkan testing
Jika melanggar DIP, kita tidak bisa melakukan unit testing dengan mudah karena semua kode saling terhubung secara langsung.

</details>
