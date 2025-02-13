package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public Product updateProduct(String id, Product updatedProduct) {
        List<Product> products = findAll();
        for (Product product : products) {
            if (product.getProductId().equals(id)) {
                product.setProductName(updatedProduct.getProductName());
                product.setProductQuantity(updatedProduct.getProductQuantity());
                return product; // Balikin produk yang sudah diupdate
            }
        }
        throw new RuntimeException("Product not found");
    }

    @Override
    public boolean deleteProduct(String id) {
        return productRepository.deleteProduct(id);
    }
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEachRemaining(products::add);
        return products;
    }
}