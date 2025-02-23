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
Reflection 1
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
reflection 2
</summary>

1. setelah menulis unit test, saya merasa lebih yakin terhadap kode yang akhirnya saya deploy atau saya kerjakan karena unit test sendiri berfungsi sebagai test untuk memastikan bahwa semua kode bekeja secara normal. unit test sangat berguna untuk menangani sistem dengan skala yang lebih besar. proses ini mendorong penulisan kode yang lebih modular dan mudah di uji

2. berapa banyak unit test yang harus saya tulis dalam 1 kelas tergantung pada kompleksitas dan jumlah fitur yang di uji contohnya seperti path yang berjalan sesuai harapan, edge case dari logika dan sistem sistem yang ada dan error handling 

3. salah satu cara untuk mengukur kecukupan unit test adalah dengan menggunakan code coverage. code coverage mengukur persentase dari kode yang sudah di cek. namun memiliki 100% coverage belum tentu kode bebas dari bug

4. ketika membuat functional test yang mirip dengan createProductFuntionalTest.java ada beberapa masalah yaitu seperti duplikasi kode yang membuat pemeliharaan lebih sulit, kurangnya sifat reusability. saran mungkin kita bisa menggunakan superclass yang berisi setup umum dan instance variable yang bisa digunakan untuk semua test suite 
</details>

<details>
<summary>
reflection 3
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