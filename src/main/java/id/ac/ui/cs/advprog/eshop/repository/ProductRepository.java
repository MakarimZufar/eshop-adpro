package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();

    // ✅ Tambahkan synchronized agar lebih thread-safe
    public synchronized Product create(Product product) {
        productData.add(product);
        return product;
    }

    // ✅ Ubah return type dari Iterator ke List
    public synchronized List<Product> findAll() {
        return new ArrayList<>(productData); // Mengembalikan copy agar tidak bisa dimodifikasi langsung
    }

    // ✅ Tambahkan method untuk update
    public synchronized Product updateProduct(String id, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null; // Bisa diganti dengan Optional agar lebih aman
    }

    public synchronized boolean deleteProduct(String id) {
        return productData.removeIf(product -> product.getProductId().equals(id));
    }
}
